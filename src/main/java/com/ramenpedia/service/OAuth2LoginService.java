package com.ramenpedia.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.ramenpedia.entity.Member;
import com.ramenpedia.enumerate.ResponseConstant;
import com.ramenpedia.exception.BusinessException;
import com.ramenpedia.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
@Slf4j
public class OAuth2LoginService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    private final MemberRepository memberRepository;

    public OAuth2LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void google(String token) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singleton(clientId))
                .build();
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken == null) {
            throw new BusinessException(ResponseConstant.UNKNOWN_ERROR);
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String name = payload.get("name").toString();
//        String birthday = payload.get("birthday").toString();
        try {
            memberRepository.save(Member.create(email, token, name, ""));
        } catch (DataIntegrityViolationException e) {
            log.info("Member has completed registration, email: {}", email, e);
        }
    }
}
