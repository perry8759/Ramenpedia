package com.ramenpedia.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Store 實體類，代表一個商店的基本信息。
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 商店的名稱。
     */
    private String name;

    /**
     * 商店所在的城市。
     */
    @Column(name = "fk_city_id")
    private String cityId;

    /**
     * 商店所在的區域。
     */
    @Column(name = "fk_district_id")
    private String district;

    /**
     * 商店的具體地址。
     */
    private String address;

    /**
     * 商店的描述信息。
     */
    private String description;

    /**
     * 商店評分。
     */
    private double score;

    /**
     * 商店的評分人數。
     */
    private int scoreCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<StoreHashtag> storeHashtags;
}
