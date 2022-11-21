package com.coderhouse.coderhouse.security;

import com.coderhouse.coderhouse.config.ApplicationProperties;
import com.coderhouse.coderhouse.utils.Constants;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final ApplicationProperties applicationProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            if(existsJwtToken(httpServletRequest)) {
                Claims claims = validateToken(httpServletRequest);
                if(Objects.nonNull(claims.get(Constants.AUTHORITIES))) {
                    setUpSpringAthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e){
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,e.getMessage());
        }
    }

    private void setUpSpringAthentication(Claims claims) {
        List<String> authorities = (List<String>) claims.get(Constants.AUTHORITIES);

        var auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(Constants.HEADER_TOKEN)
                .replace(Constants.PREFIX_TOKEN, "");
        return Jwts.parser().setSigningKey(applicationProperties.getJwtSecret().getBytes())
                .parseClaimsJws(jwtToken).getBody();
    }

    private boolean existsJwtToken(HttpServletRequest request) {
        var authenticationHeader = request.getHeader(Constants.HEADER_TOKEN);
        return authenticationHeader != null;
    }
}
