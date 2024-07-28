package com.ramenpedia.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageServiceTest {

    @Test
    void downloadImage() {
        ImageService imageService = new ImageService();
        try {
            String url = "https://lh3.googleusercontent.com/a/ACg8ocJL-Fc6PKDBDpN-kX6LTS4Pf-HRZzLaVOz9vPAkyUWAxhvwSw=s120";
            String result = imageService.downloadImage(url);
            assertNotNull(result);
        } catch (Exception e) {
            fail();
        }
    }
}