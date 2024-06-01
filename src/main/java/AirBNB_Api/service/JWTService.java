package AirBNB_Api.service;


import AirBNB_Api.entity.AppUser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {


    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;


    @Value("${jwt.expieyDuration}")
    private int expiryTIme;


    private Algorithm algorithm;


    private final static String USER_NAME = "name";


    @PostConstruct
    public void postConstruct(){
        algorithm =  Algorithm.HMAC256(algorithmKey);

    }

    public String generateToken(AppUser user){
        return  JWT.create().withClaim(USER_NAME,user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTIme))
                .withIssuer(issuer)
                .sign(algorithm);


    }

    public String getUserName(String token) {
       DecodedJWT decodedJwt =  JWT.require(algorithm).withIssuer(issuer).build().verify(token);//rosi with bonivi
       return decodedJwt.getClaim(USER_NAME).asString();

    }



}
