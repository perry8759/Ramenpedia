package com.ramenpedia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberHashtag {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "fk_member_id")
    private Long memberId;

    @Column(name = "fk_hashtag_id")
    private Long hashtagId;
}
