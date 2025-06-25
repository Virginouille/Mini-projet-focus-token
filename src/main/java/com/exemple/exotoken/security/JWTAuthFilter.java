//package com.exemple.exotoken.security;
//
//import com.exemple.exotoken.service.JwtService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@AllArgsConstructor
//public class JWTAuthFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    @Bean
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//
//        if(authHeader == null || !authHeader.startsWith("Bearer ") ) {
//           filterChain.doFilter(request, response);
//           return;
//        }
//        final String jwt = authHeader.substring(7);
//
//        final String username = jwtService.extractUsername(jwt);
//
//        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//        }
//
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//        if (jwtService.isTokenValid(jwt, userDetails) {
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    .userDetails,
//                    null,
//            userDetails.getAuthorities()
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//            filterChain.doFilter(request, response);
//        }
//
//    }
//
//
//}
