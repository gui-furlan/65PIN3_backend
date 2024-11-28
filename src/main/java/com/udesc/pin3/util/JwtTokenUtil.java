/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * @author guilh
 */
@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "6a8f8d4822c9588321d7702c8eddd733a90f2ef386c203e8d54af4956cd5ddf498e87f371efb564013c0f0a83b4bdef71015a9fc515db52cd0f90605ae870c52aa5f5c868eade725b098159fe31ab1a6b8f1e0b7ebc4bd95cff8b90819afc0bdc1962d4dec4336572ba11df991e6d9f10f4c7d56800d08c0dfbd4219a81b64b3ed278a5a1119aaaa75fc5bd0d23d8ddacc187b7b30ce1906b12de9305bd86e74a301b1f8b4c57591fb07c5381f463723cb5139f6c82f795dbd2bbb989dd27c04978f066c8874526f484ed97cb4fb5028287ff2651ce5557a8717920282c0e3a27fe40bb3498ea7a1c6cd08f8ba790e18daf65e93839a5e307c982a3f8315cc1e"; // Use pelo menos 256 bits
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private static final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256) // Novo método para assinatura
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token); // Tenta fazer o parsing. Erros indicam token inválido.
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY) // Usa a nova API para definir a chave de assinatura
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}