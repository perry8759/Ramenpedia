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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class OAuth2RegisterService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    private final MemberRepository memberRepository;
    private final ImageService imageService;

    public OAuth2RegisterService(MemberRepository memberRepository, ImageService imageService) {
        this.memberRepository = memberRepository;
        this.imageService = imageService;
    }

    public void google(String token) throws Exception {
        GoogleIdToken.Payload payload = getGooglePayload(token);
        String email = payload.getEmail();
        String name = payload.get("name").toString();
        String photo = payload.get("picture").toString();
        String img = imageService.downloadImage(photo);

        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            log.info("Member has registered, email: {}, memberId: {}", email, member.getId());
            throw new BusinessException(ResponseConstant.MEMBER_HAS_REGISTERED);
        }

        for (int i = 0; i < 3; i++) {
            try {
                memberRepository.save(Member.create(email, token, name, img));
                break;
            } catch (DataIntegrityViolationException e) {
                log.info("Member has completed registration, email: {}", email, e);
            }
        }
    }

    /**
     *
     * @param token
     * @return 是否已進行註冊
     * @throws Exception
     */
    public boolean googleCheck(String token) throws Exception {
        GoogleIdToken.Payload payload = getGooglePayload(token);
        String email = payload.getEmail();

        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            log.info("Member has registered, email: {}, memberId: {}", email, member.getId());
            return true;
        }
        return false;
    }

    /**
     *
     * @param token
     * @return 是否已進行註冊
     * @throws Exception
     */
    public Member getMember(String token) throws Exception {
        GoogleIdToken.Payload payload = getGooglePayload(token);
        String email = payload.getEmail();

        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            log.info("Member has registered, email: {}, memberId: {}", email, member.getId());
        }
        return member;
    }

    private GoogleIdToken.Payload getGooglePayload(String token) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singleton(clientId))
                .build();
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken == null) {
            log.info("Invalid token, token: {}, clientId: {}", token, clientId);
            throw new BusinessException(ResponseConstant.UNKNOWN_ERROR);
        }

        return idToken.getPayload();
    }
}
