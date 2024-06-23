package com.ramenpedia.entity;

import com.ramenpedia.enumerate.HashtagType;
import jakarta.persistence.*;
import lombok.*;

/**
 * Hashtag 實體類，代表一個標籤的基本信息。
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hashtag {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 標籤的名稱。
     */
    private String name;

    /**
     * 標籤的類型。
     */
    @Enumerated(EnumType.STRING)
    private HashtagType type;
}
