package com.ramenpedia.controller.member.dto;

import com.ramenpedia.entity.Hashtag;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetMemberInfoResp {
    private String nickname;
    private String uid;
    private String img;
    private List<Hashtag> hashtags;
    private List<Hashtag> activeAreas;
}
