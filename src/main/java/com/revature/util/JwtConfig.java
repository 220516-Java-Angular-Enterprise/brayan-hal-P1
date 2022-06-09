package com.revature.util;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JwtConfig {
    private final String salt = "asdswegdfgfdgowietuewit8942389423rlk";
    private int expirationTime = 60*60*1000;
    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private final Key signInKey;

    public JwtConfig(){
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signInKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSignInKey() {
        return signInKey;
    }
}
