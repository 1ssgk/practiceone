package com.firstvuejs.practiceone.service.jwt;

import java.util.Map;

public interface JwtService {
    public String createToken(String subject, long time) throws Exception;
    public String getSubject(String token) throws Exception;
    public boolean isUsable(String jwt) throws Exception;
    public String tokenTranslation(String jwt) throws Exception;
}
