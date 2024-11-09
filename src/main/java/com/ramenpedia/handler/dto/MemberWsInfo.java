package com.ramenpedia.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberWsInfo {
    private Long storeId;
    private String email;
    private WebSocketSession session;
}
