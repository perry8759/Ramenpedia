package com.ramenpedia.service;

import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.enumerate.HashtagType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class HashtagServiceTest {

    @Autowired
    private HashtagService hashtagService;

    @Test
    public void testGetAllHashtag() {
        List<Hashtag> hashtags = hashtagService.getHashtag(HashtagType.CITY);
        assertNotNull(hashtags);
    }
}