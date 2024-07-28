package com.ramenpedia.base;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;

public class BaseController {

    protected String getEmail(JwtAuthenticationToken jwtToken) {
        Map<String, Object> map = jwtToken.getTokenAttributes();
        return (String) map.get("email");
    }
}
