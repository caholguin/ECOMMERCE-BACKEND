package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.response.LoginResponseDTO;
import com.ecommerce.ecommerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public static LoginResponseDTO toDto(User user, String jwt){

        if(user == null) return null;

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setJwt(jwt);
        loginResponseDTO.setUser(UserMapper.toDto(user));

        return loginResponseDTO;
    }
}
