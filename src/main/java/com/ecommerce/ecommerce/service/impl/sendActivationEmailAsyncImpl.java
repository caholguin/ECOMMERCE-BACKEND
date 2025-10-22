package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class sendActivationEmailAsyncImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public sendActivationEmailAsyncImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendActivationEmailAsync(String toEmail, String activationToken){
        try {
            String activationUrl = frontendUrl + "/auth/activate-account?token=" + activationToken;

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Activa tu cuenta");

            String htmlContent = buildActivationEmail(activationUrl);
            helper.setText(htmlContent, true);

            mailSender.send(message);

            // Log exitoso
            //log.info("Email de activación enviado a: {}", toEmail);

        } catch (Exception e) {
            // Log del error pero NO lanzar excepción (es asíncrono)
            System.out.println("Error al enviar email de activación a {}: {}" + toEmail + e.getMessage());
            //log.error("Error al enviar email de activación a {}: {}", toEmail, e.getMessage());

            // Opcional: Guardar en tabla de emails_fallidos para reintentar después
        }


    }

    private String buildActivationEmail(String activationUrl) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
            </head>
            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px;">
                    <h2 style="color: #4CAF50;">¡Bienvenido!</h2>
                    
                    <p>Gracias por registrarte. Para activar tu cuenta, haz clic en el siguiente enlace:</p>
                    
                    <div style="text-align: center; margin: 30px 0;">
                        <a href="%s" 
                           style="background-color: #4CAF50; color: white; padding: 12px 30px; 
                                  text-decoration: none; border-radius: 5px; display: inline-block;">
                            Activar Cuenta
                        </a>
                    </div>
                    
                    <p style="color: #666; font-size: 14px;">
                        Este enlace expirará en 24 horas.
                    </p>
                    
                    <p style="color: #999; font-size: 12px; margin-top: 30px;">
                        Si no creaste esta cuenta, puedes ignorar este correo.
                    </p>
                </div>
            </body>
            </html>
            """.formatted(activationUrl);
    }
}
