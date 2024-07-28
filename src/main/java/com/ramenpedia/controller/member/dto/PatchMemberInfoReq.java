package com.ramenpedia.controller.member.dto;

import com.ramenpedia.base.BaseRequest;
import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.exception.ArgumentException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PatchMemberInfoReq extends BaseRequest {

    private String nickname;
    private String img;
    private List<Hashtag> hashtags;
    private List<Hashtag> activeAreas;

    @Override
    public void valid() throws ArgumentException {

    }
}
