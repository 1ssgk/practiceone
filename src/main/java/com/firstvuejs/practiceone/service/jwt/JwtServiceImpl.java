package com.firstvuejs.practiceone.service.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.firstvuejs.exception.UnauthorizedException;
import com.google.common.collect.Maps;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService{

    private static final String SECRET_KEY = "authtoken";

    // 토큰 발행
    @Override
    public String createToken(String subject, long time) {
        if (time <= 0) {
            throw new RuntimeException("Expiry time must be greater than Zero : ["+time+"] ");
        }
        // 토큰을 서명하기 위해 사용해야할 알고리즘 선택
        SignatureAlgorithm  signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);
        long nowTime = System.currentTimeMillis();
        builder.setExpiration(new Date(nowTime + time));
        return builder.compact();
    }

    // 토큰 해독 (ID 반환)
    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // 토큰의 유효성 체크
    @Override
    public boolean isUsable(String jwt) {
        try{
            Claims claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(jwt)
            .getBody();

            return true;

        }catch (Exception e) {
            return false;
            //throw new UnauthorizedException("err");
        }
    }

    @Override
    public String tokenTranslation(String jwt) throws Exception {
        // TODO Auto-generated method stub

        Map<String,Object> resultMap = Maps.newHashMap();
        
        Claims claims = Jwts.parser()
                            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                            .parseClaimsJws(jwt)
                            .getBody();

        return claims.getSubject();
    }
}