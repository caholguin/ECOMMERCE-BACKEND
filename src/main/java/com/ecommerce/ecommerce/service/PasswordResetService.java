package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ForgotPasswordRequestDTO;
import com.ecommerce.ecommerce.dto.request.ResendCodeRequestDTO;
import com.ecommerce.ecommerce.dto.request.ResetPasswordRequestDTO;
import com.ecommerce.ecommerce.dto.request.VerifyCodeRequestDTO;

public interface PasswordResetService {

    void sendResetCode(String email);

    String verifyResetCode(VerifyCodeRequestDTO verifyCodeRequestDTO);

    void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO);
}
