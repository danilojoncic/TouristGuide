package com.example.touristguide.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.touristguide.dto.UserJWTResponse;
import com.example.touristguide.dto.UserLoginDto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JWTCoder {


    public static final String SECRET_KEY = "super duper tajna sifra";

    /**
     * Uzima pruzene podatke i od njih pakuje jwt token
     * @param firstname ime korisnika koje cemo da uzmemo iz baze
     * @param lastname prezime korsinika koje cemo da uzmemo iz baze
     * @param tip uloga korisnika koju cemo da uzmemo iz baze
     * @return vracamo UserJWTResponse dto objekat koji sam sadrzi polje token, unutar
     * tog polja ce se nalaziti enkodirani podaci u formatu jwt-a
     */
    public static UserJWTResponse encode(String firstname, String lastname, String tip){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            String token = JWT.create()
                    .withClaim("firstname", firstname)
                    .withClaim("lastname", lastname)
                    .withClaim("tip", tip)
                    .sign(algorithm);
            return new UserJWTResponse(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
