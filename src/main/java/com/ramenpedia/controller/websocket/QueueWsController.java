package com.ramenpedia.controller.websocket;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.base.BaseController;
import com.ramenpedia.controller.websocket.dto.*;
import com.ramenpedia.service.QueueWsService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;

@Controller
public class QueueWsController extends BaseController {

    @Autowired
    private QueueWsService queueWsService;

    /**
     * 回報營業時間
     */
    @MessageMapping("/business-hours/{storeId}")
    @SendTo("/topic/queue-websocket/{storeId}")
    public ApiResponse<BusinessHoursSend> businessHours(@PathParam("storeId") Long storeId, BusinessHoursMessage message,
                                                        JwtAuthenticationToken jwtToken) throws Exception {
        return ApiResponse.getSuccessInstance(queueWsService.businessHours(storeId, message, getEmail(jwtToken)));
    }

    /**
     * 回報限量拉麵剩餘數量
     */
    @MessageMapping("/queue/{storeId}")
    @SendTo("/topic/queue-websocket/{storeId}")
    public ApiResponse<QueueSend> queue(@PathParam("storeId") Long storeId, QueueMessage message,
                                        JwtAuthenticationToken jwtToken) throws Exception {
        return ApiResponse.getSuccessInstance(queueWsService.queue(storeId, message, getEmail(jwtToken)));
    }

    /**
     * 回報排隊人數
     */
    @MessageMapping("/limited/{storeId}")
    @SendTo("/topic/queue-websocket/{storeId}")
    public ApiResponse<LimitedSend> limited(@PathParam("storeId") Long storeId, LimitedMessage message,
                                            JwtAuthenticationToken jwtToken) throws Exception {
        return ApiResponse.getSuccessInstance(queueWsService.limited(storeId, message, getEmail(jwtToken)));
    }
}
