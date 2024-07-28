package com.ramenpedia.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CodeGeneratingServiceTest {

    @Test
    void getUid() {
        String uid = CodeGeneratingService.getUid();
        log.info("uid: {}", uid);
        assertNotNull(uid);
        assertEquals(10, uid.length());
    }
}