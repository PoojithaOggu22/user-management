package com.bilvantis.user.app.service.config;

import com.bilvantis.user.api.service.impl.JwtTokenService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Pattern;

import static com.bilvantis.user.app.service.util.UserAppConstant.*;

@Component
public class AuthenticationFilterConfig extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHORIZATION);
        String token = null;
        String employeeId = null;
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(BEARER)) {
            token = authHeader.substring(BEARER.length());
            employeeId = jwtTokenService.extractUsername(token);
        }
        if(StringUtils.isBlank(token)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), TOKEN_NOT_PASSED);
            return;
        }
        try {
            jwtTokenService.validateToken(token, employeeId);
        } catch (Exception e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), TOKEN_INVALID);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        Pattern noAuthPattern = Pattern.compile(NO_AUTH_ENDPOINT, Pattern.CASE_INSENSITIVE);
        Pattern authPattern = Pattern.compile(AUTH_ENDPOINT, Pattern.CASE_INSENSITIVE);

        return (authPattern.matcher(path).find() || noAuthPattern.matcher(path).find());
    }
}
