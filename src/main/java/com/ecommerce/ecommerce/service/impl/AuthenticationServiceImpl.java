package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.LoginRequestDTO;
import com.ecommerce.ecommerce.dto.request.RefreshTokenDTO;
import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.dto.response.LoginResponseDTO;
import com.ecommerce.ecommerce.dto.response.RegisteredUserDTO;
import com.ecommerce.ecommerce.dto.response.UserDTO;
import com.ecommerce.ecommerce.entity.JwtToken;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.CategoryMapper;
import com.ecommerce.ecommerce.mapper.LoginMapper;
import com.ecommerce.ecommerce.mapper.UserMapper;
import com.ecommerce.ecommerce.repository.epecification.JwtTokenRepository;
import com.ecommerce.ecommerce.service.AuthenticationService;
import com.ecommerce.ecommerce.service.JwtService;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenRepository jwtRepository;

    public AuthenticationServiceImpl(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, JwtTokenRepository jwtRepository){
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.jwtRepository = jwtRepository;
    }

    @Override
    public LoginResponseDTO registerCustomer(SaveUserDTO saveUserDTO){

        User user = userService.registerCustomer(saveUserDTO);
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        saveUserToken(user,jwt);


        return LoginMapper.toDto(user,jwt);

        //return registeredUserDTO;
    }

    private Map<String, Object> generateExtraClaims(User user){

        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().getName());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findByUsername(loginRequestDTO.getUsername()).get();

        String jwt = jwtService.generateToken(user,generateExtraClaims((User) user));
        saveUserToken((User) user,jwt);

        return LoginMapper.toDto((User) user,jwt);
    }


    @Override
    public boolean validateToken(String jwt){
        try {
            jwtService.extractUsername(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserDTO findLoggedInUser(){
        Authentication auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        String username = (String) auth.getPrincipal();

        User user = userService.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Usuario no encontrado"));


        return UserMapper.toDto(user);
    }

    @Override
    public void logout(HttpServletRequest request){

        String jwt = jwtService.extractJwtFromRequest(request);

        if (jwt == null || !StringUtils.hasText(jwt)) return;

        Optional<JwtToken> token = jwtRepository.findByToken(jwt);

        if (token.isPresent() && token.get().isValid()){
            token.get().setValid(false);
            jwtRepository.save(token.get());
        }
    }

    private void saveUserToken(User user, String jwt){
        JwtToken token = new JwtToken();
        token.setToken(jwt);
        token.setUser(user);
        token.setExpiration(jwtService.extractExpiration(jwt));
        token.setValid(true);

        jwtRepository.save(token);
    }

    public LoginResponseDTO refreshToken(String jwt){

        Optional<JwtToken> token = jwtRepository.findByToken(jwt);

        if (token.isPresent() && token.get().isValid()) {

            String username = jwtService.extractUsername(jwt);

            User user = userService.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Usuario no encontrado."));

            String newJwt = jwtService.generateToken(user, generateExtraClaims(user));

            saveUserToken(user,newJwt);

            token.get().setValid(false);
            jwtRepository.save(token.get());

            return LoginMapper.toDto((User) user,jwt);
        }

        throw new ObjectNotFoundException("El token proporcionado no pertenece a un usuario o es invalido");

    }

}