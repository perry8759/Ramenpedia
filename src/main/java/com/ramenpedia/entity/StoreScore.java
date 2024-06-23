package com.ramenpedia.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * StoreScore 實體類，代表一個商店評分的基本信息。
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreScore {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 評分對應的商店ID。
     */
    @Column(name = "fk_store_id")
    private Long storeId;

    /**
     * 評分對應的會員ID。
     */
    @Column(name = "fk_member_id")
    private Long memberId;

    /**
     * 商店的評分。
     */
    private double score;

    /**
     * 會員對商店的評論。
     */
    private String comment;
}