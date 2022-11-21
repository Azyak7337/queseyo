package com.coderhouse.coderhouse.security;

import com.coderhouse.coderhouse.config.ApplicationProperties;
import com.coderhouse.coderhouse.utils.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider implements Serializable {
    private final ApplicationProperties applicationProperties;

    public String getJwToken(String username) {
        var grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(Constants.ROLE);

        return Jwts.builder()
                .setSubject(username)
                .claim(Constants.AUTHORITIES,grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + applicationProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, applicationProperties.getJwtSecret().getBytes())
                .compact();
    }
}
