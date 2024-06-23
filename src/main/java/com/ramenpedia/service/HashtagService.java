package com.ramenpedia.service;

import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.enumerate.HashtagType;
import com.ramenpedia.repository.HashtagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public List<Hashtag> getHashtag(HashtagType type) {
        log.info("getHashtag");
        return hashtagRepository.findAllByType(type);
    }
}
