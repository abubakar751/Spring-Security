package com.sb.security.filter;

import com.sb.security.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizeHeader = request.getHeader("Authorization");
        String token;
        String userName =null;
   if (StringUtils.hasLength(authorizeHeader) && authorizeHeader.startsWith("Bearer")){
      token= authorizeHeader.substring(7);
      userName= jwtService.extractUserName(token);

   }
   if (StringUtils.hasLength(userName)&& SecurityContextHolder.getContext().getAuthentication()==null){

   }
    }
}
