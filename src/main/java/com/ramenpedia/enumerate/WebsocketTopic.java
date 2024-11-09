package com.ramenpedia.enumerate;

public enum WebsocketTopic {
    SUBSCRIPTION("subscription"),
    BUSINESS_HOURS("business-hours"),
    QUEUE("queue"),
    LIMITED("limited");

    private final String topic;

    WebsocketTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
