package com.ramenpedia.service;

import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.entity.MemberHashtag;
import com.ramenpedia.enumerate.ResponseConstant;
import com.ramenpedia.exception.BusinessException;
import com.ramenpedia.repository.HashtagRepository;
import com.ramenpedia.repository.MemberHashtagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MemberHashtagService {

    @Autowired
    private MemberHashtagRepository memberHashtagRepository;
    @Autowired
    private HashtagRepository hashtagRepository;

    public void save(List<Hashtag> hashtags, Long memberId) {
        List<MemberHashtag> memberHashtags = new ArrayList<>();
        for (Hashtag hashtag : hashtags) {
            Hashtag hashtagEntity = hashtagRepository.findById(hashtag.getId()).orElse(null);
            if (hashtagEntity == null) {
                log.error("Hashtag not found, hashtagId: {}", hashtag.getId());
                throw new BusinessException(ResponseConstant.HASHTAG_NOT_FOUND);
            }
            MemberHashtag memberHashtag = new MemberHashtag();
            memberHashtag.setMemberId(memberId);
            memberHashtag.setHashtagId(hashtag.getId());
            memberHashtags.add(memberHashtag);
        }
        memberHashtagRepository.saveAll(memberHashtags);
    }
}
