package com.security.jwt.business;

import com.security.jwt.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserRules {

    public UserDTO getToken(String userName, String pwd){
        String token = getJWTToken(userName);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setToken(token);
        log.info("user {}", userName);
        log.info("token {}", token);
        return userDTO;

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();

        return "Bearer " + token;
    }
}
