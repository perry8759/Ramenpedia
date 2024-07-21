package com.ramenpedia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"token"})
@EqualsAndHashCode(of="id")
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String email;

    private String token;

    private String name;

    private String birthday;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob img;

    private Long createMillis;

    private Long privacyPolicyMillis;

    public static Member create(String email, String token, String name, Blob img, String birthday) {
        return new Member(null, email, token, name, birthday, img, System.currentTimeMillis(), null);
    }
}
