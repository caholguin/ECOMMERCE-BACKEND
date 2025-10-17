package com.ecommerce.ecommerce.service;

public interface EmailService {

    void sendActivationEmailAsync(String toEmail, String activationToken);
}
