package com.ramenpedia.service;

import com.ramenpedia.entity.Member;
import com.ramenpedia.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PrivacyPolicyService {

    private final MemberRepository memberRepository;

    public PrivacyPolicyService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void privacyPolicy(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail);
        member.setPrivacyPolicyMillis(System.currentTimeMillis());
        memberRepository.save(member);
    }
}
