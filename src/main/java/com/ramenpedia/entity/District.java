package com.ramenpedia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class District {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 區域的名稱。
     */
    private String name;

    /**
     * 區域對應的城市。
     */
    @Column(name = "fk_city_id")
    private String cityId;
}
