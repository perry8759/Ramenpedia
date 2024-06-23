package com.ramenpedia.controller.hashtag.dto;

import com.ramenpedia.base.BaseRequest;
import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.exception.ArgumentException;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetHashtagResp {
    List<Hashtag> hashtagList;
}
