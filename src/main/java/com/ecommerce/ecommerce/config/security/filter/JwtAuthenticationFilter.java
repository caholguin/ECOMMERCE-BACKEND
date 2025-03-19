package com.ecommerce.ecommerce.config.security.filter;

import com.ecommerce.ecommerce.entity.JwtToken;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.repository.epecification.JwtTokenRepository;
import com.ecommerce.ecommerce.service.JwtService;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    private final JwtTokenRepository jwtTokenRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserService userService, JwtTokenRepository jwtTokenRepository){
        this.jwtService = jwtService;
        this.userService = userService;
        this.jwtTokenRepository = jwtTokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        String jwt = jwtService.extractJwtFromRequest(request);

        if (jwt == null || !StringUtils.hasText(jwt)){
            filterChain.doFilter(request,response);
            return;
        }

        Optional<JwtToken> token = jwtTokenRepository.findByToken(jwt);
        boolean isValid = validateToken(token);

        if (!isValid){
            filterChain.doFilter(request,response);
            return;
        }

        String username = jwtService.extractUsername(jwt);

        User user = userService.findByUsername(username).orElseThrow(()-> new ObjectNotFoundException("Usuario con email " +username+ " no encontrado"));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);
    }

    private boolean validateToken(Optional<JwtToken> optionToken){
        if (optionToken.isEmpty()){
            System.out.println("token no valido = " + optionToken);
            return false;
        }

        JwtToken token = optionToken.get();

        Date now = new Date(System.currentTimeMillis());

        boolean isValid = token.isValid() && token.getExpiration().after(now);

        if (!isValid){
            System.out.println("token invalido");
            updateTokenStatus(token);
        }

        return  isValid;
    }

    private void updateTokenStatus(JwtToken token){
        token.setValid(false);
        jwtTokenRepository.save(token);
    }
}
