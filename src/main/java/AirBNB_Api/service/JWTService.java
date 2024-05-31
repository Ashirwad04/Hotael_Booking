package AirBNB_Api.service;


import AirBNB_Api.entity.AppUser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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


    @PostConstruct
    public void postConstruct(){
        algorithm =  Algorithm.HMAC256(algorithmKey);

    }

    public String generateToken(AppUser user){
        return  JWT.create().withClaim("name",user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTIme))
                .withIssuer(issuer)
                .sign(algorithm);


    }



}
