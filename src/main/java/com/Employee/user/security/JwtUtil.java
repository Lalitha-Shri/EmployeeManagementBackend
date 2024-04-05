package com.Employee.user.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service

public class JwtUtil {
    private  static final int expiryInMs=300*60*60;
    private static  final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String generate(String username)
    {
      return  Jwts.builder().setSubject(username)
                .setIssuer("test.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiryInMs))
                .signWith(key)
                .compact();
    }
    public  String getUsername(String token)
    {
        Claims claims= getClaims(token);
        return  claims.getSubject();

    }
    public boolean isExpired(String token){
      Claims claims= getClaims(token);
      return  claims.getExpiration().after(new Date(System.currentTimeMillis()));

    }
     private  Claims getClaims(String token){
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
     }
     public  Boolean validate(String token)
     {
         if(isExpired(token) && getUsername(token)!=null){
             return  true;
         }
         return  false;
     }
}
