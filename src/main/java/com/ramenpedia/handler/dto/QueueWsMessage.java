package com.ramenpedia.handler.dto;

import com.ramenpedia.enumerate.WebsocketTopic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueueWsMessage {
    private WebsocketTopic topic;
    private LinkedHashMap<String, String> content;
}
