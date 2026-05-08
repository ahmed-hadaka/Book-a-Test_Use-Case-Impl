package com.java;


public interface AuthenticationService {
    boolean checkAuthentication(String userId, String token);
}
