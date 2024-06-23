package com.ramenpedia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreHashtag {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "fk_store_id")
    private Long storeId;

    @Column(name = "fk_hashtag_id")
    private Long hashtagId;

    @ManyToOne
    @JoinColumn(name = "fk_store_id", insertable = false, updatable = false)
    private Store store;
}

