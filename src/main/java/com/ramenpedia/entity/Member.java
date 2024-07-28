package com.ramenpedia.entity;

import com.ramenpedia.service.CodeGeneratingService;
import jakarta.persistence.*;
import lombok.*;

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
    private String img;

    private Long createMillis;

    private Long privacyPolicyMillis;

    private String uid;

    private String nickname;

    public static Member create(String email, String token, String name) {
        return new Member(null, email, token, name, null, null, System.currentTimeMillis(), null, CodeGeneratingService.getUid(), name);
    }
}
