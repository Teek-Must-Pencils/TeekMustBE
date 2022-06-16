package com.binar.teekmustbe.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilterConfig extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthFilterConfig.class);
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserDetailsService userDetailsService;

    public AuthFilterConfig() {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = jwtUtil.parseJwt(request);
        if (token.isPresent()) {
            var claims = jwtUtil.validateJwtToken(token.get());
            if (claims.isPresent()) {
                var userDetails = userDetailsService.loadUserByUsername(claims.get().getSubject());
                var authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            logger.info("Filtered");
            filterChain.doFilter(request, response);
        } else {
            logger.info("no auth header found");
            filterChain.doFilter(request, response);
        }

    }
}
