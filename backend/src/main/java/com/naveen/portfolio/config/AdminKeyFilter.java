package com.naveen.portfolio.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class AdminKeyFilter extends OncePerRequestFilter {

    @Value("${app.admin-key}")
    private String adminKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String method = request.getMethod();
        String path = request.getRequestURI();

        boolean readOnly = method.equals("GET")
                || method.equals("HEAD")
                || method.equals("OPTIONS");
        boolean contactForm = method.equals("POST") && path.equals("/api/contact");

        if (path.startsWith("/api/") && !readOnly && !contactForm) {
            String provided = request.getHeader("X-Admin-Key");
            boolean valid = provided != null && MessageDigest.isEqual(
                    provided.getBytes(StandardCharsets.UTF_8),
                    adminKey.getBytes(StandardCharsets.UTF_8));

            if (!valid) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Forbidden\"}");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}