package com.ramenpedia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public static Member create(String email, String token, String name, String birthday) {
        return new Member(null, email, token, name, birthday);
    }
}
