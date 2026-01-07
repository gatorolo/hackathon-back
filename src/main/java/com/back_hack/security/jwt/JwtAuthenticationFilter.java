package com.back_hack.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenfromRequest(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);

    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // LOG de depuración (lo verás en la consola de Koyeb)
        System.out.println("Filtro ejecutándose para la ruta: " + path);

        // 1. SI ES AUTH, PASAR DE LARGO SIN PREGUNTAR
        if (path.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = getTokenfromRequest(request);

        // 2. Si no hay token en otras rutas, sigue normal (Spring rebotará si es necesario)
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }


        private String getTokenfromRequest (HttpServletRequest request){

            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
                return authHeader.substring(7);
            }
            return null;
        }


}
