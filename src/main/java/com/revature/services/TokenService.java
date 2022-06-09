package com.revature.services;

import com.revature.dtos.responses.Principle;
import com.revature.util.JwtConfig;
import com.revature.util.annotations.Inject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenService {
    @Inject
    private JwtConfig jwtConfig;

    public TokenService(){
        super();
    }

    @Inject
    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principle subject){
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getUser_id())
                .setIssuer("reimbursement101")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now+ jwtConfig.getExpirationTime()))
                .setSubject(subject.getUsername())
                .claim("role", subject.getRole())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSignInKey());
        return tokenBuilder.compact();
    }

    public Principle extractRequesterDetails(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSignInKey())
                    .parseClaimsJws(token)
                    .getBody();
            return new Principle(claims.getId(), claims.getSubject(), claims.get("role", String.class));
        }catch(Exception e){
            return null;
        }

    }
}
