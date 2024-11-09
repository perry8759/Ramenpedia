package com.ramenpedia.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramenpedia.entity.Member;
import com.ramenpedia.enumerate.StoreBusinessStatus;
import com.ramenpedia.handler.dto.*;
import com.ramenpedia.service.OAuth2RegisterService;
import com.ramenpedia.service.QueueWsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class QueueWsHandler extends TextWebSocketHandler {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    // session id / member info object
    private static Map<String, MemberWsInfo> sessionMap = new ConcurrentHashMap<>();

    // store id / session object
    private static Map<Long, List<WebSocketSession>> storeSessionMap = new ConcurrentHashMap<>();

    private final QueueWsService queueWsService;
    private final OAuth2RegisterService oAuth2RegisterService;

    public QueueWsHandler(QueueWsService queueWsService, OAuth2RegisterService oAuth2RegisterService) {
        this.queueWsService = queueWsService;
        this.oAuth2RegisterService = oAuth2RegisterService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established: {}", session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Message received: {}", message.getPayload());
        QueueWsMessage queueWsMessage;
        try {
            queueWsMessage = objectMapper.readValue(message.getPayload(), QueueWsMessage.class);
        } catch (Exception e) {
            log.error("Failed to parse message", e);
            session.sendMessage(new TextMessage("Failed to parse message"));
            return;
        }

        Long storeId;
        String email;
        String json;
        switch (queueWsMessage.getTopic()) {
            case SUBSCRIPTION:
                // 驗證是否有訂閱權限
                Member member = oAuth2RegisterService.getMember(queueWsMessage.getContent().get("token"));
                if (member == null) {
                    session.sendMessage(new TextMessage("Unauthorized"));
                    return;
                }
                subscriptionSuccess(session, member, Long.valueOf(queueWsMessage.getContent().get("storeId")));
                session.sendMessage(new TextMessage("Subscription success"));
                break;
            case BUSINESS_HOURS:
                // 處理營業時間回報
                storeId = sessionMap.get(session.getId()).getStoreId();
                email = sessionMap.get(session.getId()).getEmail();

                json = objectMapper.writeValueAsString(
                        queueWsService.businessHours(StoreBusinessStatus.valueOf(queueWsMessage.getContent().get("status")), storeId, email));
                sendMessageByStoreId(storeSessionMap.get(storeId), json);
                break;
            case QUEUE:
                // 回報排隊人數
                storeId = sessionMap.get(session.getId()).getStoreId();
                email = sessionMap.get(session.getId()).getEmail();

                json = objectMapper.writeValueAsString(
                        queueWsService.queue(Integer.valueOf(queueWsMessage.getContent().get("nowQueuePersonCount")), storeId, email));
                sendMessageByStoreId(storeSessionMap.get(storeId), json);
                break;
            case LIMITED:
                // 回報限量拉麵剩餘數量
                storeId = sessionMap.get(session.getId()).getStoreId();
                email = sessionMap.get(session.getId()).getEmail();

                json = objectMapper.writeValueAsString(
                        queueWsService.limited(Integer.valueOf(queueWsMessage.getContent().get("remainingQuantity")), storeId, email));
                sendMessageByStoreId(storeSessionMap.get(storeId), json);
                break;
            default:
                log.error("Unknown topic: {}", queueWsMessage.getTopic());
                session.sendMessage(new TextMessage("Unknown topic"));
                break;
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed, sessionId: {}, status: {}", session.getId(), status);
        if (sessionMap.get(session.getId()) != null) {
            sessionMap.remove(session.getId());
        }
        session.close();
    }

    private void subscriptionSuccess(WebSocketSession session, Member member, Long storeId) {
        sessionMap.put(session.getId(), new MemberWsInfo(storeId, member.getEmail(), session));

        if (!storeSessionMap.containsKey(storeId)) {
            storeSessionMap.put(storeId, new ArrayList<>());
        }
        storeSessionMap.get(storeId).add(session);
    }

    private void sendMessageByStoreId(List<WebSocketSession> sessionList, String message) {
        for (WebSocketSession s : sessionList) {

            if (!s.isOpen()) {
                sessionList.remove(s);
                log.error("Session is not open, sessionId: {}", s.getId());
                continue;
            }

            try {
                s.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                log.error("Failed to send message", e);
            }
        }
    }
}
