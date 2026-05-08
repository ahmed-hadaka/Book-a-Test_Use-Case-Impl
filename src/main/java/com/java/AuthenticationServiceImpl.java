package com.java;

import java.util.Map;


public class AuthenticationServiceImpl implements AuthenticationService {

    private final Map<String, String> validSessions = Map.of(
            "user-001", "token-abc123",
            "user-002", "token-xyz789"
    );

    @Override
    public boolean checkAuthentication(String userId, String token) {
        System.out.println("[Auth] Checking authentication for user: " + userId);
        boolean result = token != null && token.equals(validSessions.get(userId));
        System.out.println("[Auth] Authentication result: " + (result ? "VALID" : "INVALID"));
        return result;  
    }
}
