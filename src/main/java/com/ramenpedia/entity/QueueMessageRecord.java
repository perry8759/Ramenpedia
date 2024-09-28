package com.ramenpedia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessageRecord {
    public enum Type {
        BUSINESS_HOURS,
        LIMITED,
        QUEUE
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String message;

    @Column(name = "fk_member_id")
    private Long memberId;

    @Column(name = "fk_store_id")
    private Long storeId;

    private Long createMillis;

    public QueueMessageRecord(Long memberId, Long storeId, Type type, String message) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.type = type;
        this.message = message;
        this.createMillis = System.currentTimeMillis();
    }
}
