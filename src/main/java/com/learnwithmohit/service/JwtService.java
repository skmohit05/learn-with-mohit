package com.learnwithmohit.service;

import com.learnwithmohit.entity.JwtRequest;
import com.learnwithmohit.entity.JwtResponse;

public interface JwtService {
    JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;
}
