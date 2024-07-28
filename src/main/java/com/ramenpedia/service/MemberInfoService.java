package com.ramenpedia.service;

import com.ramenpedia.controller.member.dto.GetMemberInfoResp;
import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.entity.Member;
import com.ramenpedia.enumerate.HashtagType;
import com.ramenpedia.enumerate.ResponseConstant;
import com.ramenpedia.exception.BusinessException;
import com.ramenpedia.repository.HashtagRepository;
import com.ramenpedia.repository.MemberHashtagRepository;
import com.ramenpedia.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class MemberInfoService {
    private final MemberRepository memberRepository;
    private final HashtagRepository hashtagRepository;
    private final MemberHashtagRepository memberHashtagRepository;
    private final MemberHashtagService memberHashtagService;

    public MemberInfoService(MemberRepository memberRepository, HashtagRepository hashtagRepository,
                             MemberHashtagRepository memberHashtagRepository, MemberHashtagService memberHashtagService) {
        this.memberRepository = memberRepository;
        this.hashtagRepository = hashtagRepository;
        this.memberHashtagRepository = memberHashtagRepository;
        this.memberHashtagService = memberHashtagService;
    }

    public GetMemberInfoResp getInfo(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail);
        if (member == null) {
            log.error("Member not found, memberEmail: {}", memberEmail);
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }
        List<Hashtag> memberHashtagsByCity = hashtagRepository.findByMemberIdAndType(member.getId(), HashtagType.CITY.name());

        List<Hashtag> memberHashtagsByNotCity = hashtagRepository.findByMemberIdAndNotType(member.getId(), HashtagType.CITY.name());

        return new GetMemberInfoResp(member.getNickname(), member.getUid(), member.getImg(),
                memberHashtagsByNotCity, memberHashtagsByCity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void patchInfo(String email, String nickname, String img, List<Hashtag> hashtags,
                          List<Hashtag> activeAreas) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            log.error("Member not found, email: {}", email);
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        if (nickname != null) {
            member.setNickname(nickname);
        }
        if (img != null) {
            member.setImg(img);
        }
        memberRepository.save(member);

        if (hashtags != null && !hashtags.isEmpty()) {
            memberHashtagRepository.deleteAllByMemberIdAndNotType(member.getId(), HashtagType.CITY.name());
            memberHashtagService.save(hashtags, member.getId());
        }

        if (activeAreas != null && !activeAreas.isEmpty()) {
            memberHashtagRepository.deleteAllByMemberIdAndType(member.getId(), HashtagType.CITY.name());
            memberHashtagService.save(activeAreas, member.getId());
        }
    }
}
