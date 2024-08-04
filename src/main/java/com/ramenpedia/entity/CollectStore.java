package com.ramenpedia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of="id")
public class CollectStore {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "fk_member_id")
    private Long memberId;

    @Column(name = "fk_store_id")
    private Long storeId;

    @ManyToOne
    private Store store;
}
