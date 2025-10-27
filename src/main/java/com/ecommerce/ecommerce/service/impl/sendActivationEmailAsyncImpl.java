package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Year;

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

    @Override
    @Async
    public void sendResetEmail(String email, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(email);
            helper.setSubject("Código de recuperación de contraseña");

            String supportEmail = "support@gmail.com";

            // Generamos el HTML con el código
            String htmlContent = buildResetEmail(code, 15, supportEmail);
            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar email de recuperación: " + e.getMessage(), e);
        }
    }

    @Override
    @Async
    public void sendPasswordChangedEmail(String email){
        try {
            String url = frontendUrl + "/auth/login";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(email);
            helper.setSubject("Cambio de contraseña");

            String htmlContent = buildPasswordChanged(url);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar email: " + e.getMessage(), e);
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

    private String buildResetEmail(String code, int expirationMinutes, String supportEmail) {
        int year = Year.now().getValue();
        return """
                <!doctype html>
                <html lang="es">
                  <head>
                    <meta charset="utf-8" />
                    <meta name="viewport" content="width=device-width,initial-scale=1" />
                    <title>Código de verificación</title>
                  </head>
                  <body style="margin:0;padding:0;background-color:#f4f6f8;font-family:system-ui,-apple-system,Segoe UI,Roboto,'Helvetica Neue',Arial,sans-serif;color:#0f172a;">
                    <table width="100%%" cellpadding="0" cellspacing="0" role="presentation">
                      <tr>
                        <td align="center" style="padding:24px;">
                          <table width="600" cellpadding="0" cellspacing="0" role="presentation" style="max-width:600px;background:#ffffff;border-radius:8px;overflow:hidden;box-shadow:0 6px 18px rgba(15,23,42,0.08);">
                
                            <tr>
                              <td style="padding:24px 24px 12px 24px;text-align:center;">
                                <h1 style="margin:0;font-size:20px;line-height:1.2;color:#0f172a;font-weight:600;">Código de verificación</h1>
                              </td>
                            </tr>
                
                            <tr>
                              <td style="padding:12px 24px 16px 24px;font-size:15px;line-height:1.5;color:#374151;">
                                <p style="margin:0 0 12px 0;">Hola,</p>
                                <p style="margin:0 0 18px 0;">
                                  Has solicitado restablecer tu contraseña. Usa el siguiente código para continuar con el proceso:
                                </p>
                
                                <table cellpadding="0" cellspacing="0" role="presentation" style="margin:24px auto;">
                                  <tr>
                                    <td align="center" style="padding:16px 24px;border-radius:8px;background:#f1f5f9;font-size:32px;font-weight:700;letter-spacing:6px;color:#2563eb;">
                                      %s
                                    </td>
                                  </tr>
                                </table>
                
                                <p style="margin:0 0 12px 0;">Este código expirará en <strong>%d minutos</strong>.</p>
                                <p style="margin:0 0 12px 0;">Si no solicitaste este cambio, puedes ignorar este mensaje.</p>
                              </td>
                            </tr>
                
                            <tr>
                              <td style="padding:16px 24px 24px 24px;font-size:13px;color:#6b7280;background:#f8fafc;">
                                <p style="margin:0 0 8px 0;">¿Necesitas ayuda? Escríbenos a
                                  <a href="mailto:%s" style="color:#2563eb;text-decoration:underline;">%s</a>
                                </p>
                                <p style="margin:0;color:#9ca3af;">&copy; %d TuApp. Todos los derechos reservados.</p>
                              </td>
                            </tr>
                          </table>
                
                          <table width="600" cellpadding="0" cellspacing="0" role="presentation" style="max-width:600px;margin-top:12px;">
                            <tr>
                              <td style="font-size:12px;color:#9ca3af;text-align:center;">
                                No compartas este código con nadie. Nuestro equipo nunca te pedirá este código por correo o teléfono.
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                    </table>
                  </body>
                </html>
                """.formatted(code, expirationMinutes, supportEmail, supportEmail, year);
    }

    private String buildPasswordChanged(String url) {
        return """
        <!DOCTYPE html>
        <html lang="es">
        <head>
            <meta charset="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>Contraseña actualizada</title>
            <style>
                body {
                    font-family: 'Inter', sans-serif;
                    background-color: #f3f4f6;
                    color: #1f2937;
                    margin: 0;
                    padding: 0;
                }
                .container {
                    max-width: 480px;
                    margin: 40px auto;
                    background-color: #ffffff;
                    border-radius: 0.75rem;
                    padding: 32px 24px;
                    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                    text-align: center;
                }
                .title {
                    font-size: 1.5rem;
                    font-weight: 600;
                    color: #111827;
                    margin-bottom: 16px;
                }
                .message {
                    font-size: 1rem;
                    color: #374151;
                    margin-bottom: 24px;
                }
                .divider {
                    width: 100%%;
                    height: 1px;
                    background-color: #e5e7eb;
                    margin: 24px 0;
                }
                .footer {
                    font-size: 0.875rem;
                    color: #6b7280;
                    margin-top: 16px;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1 class="title">¡Tu contraseña ha sido actualizada!</h1>
                <p class="message">
                    Te confirmamos que la contraseña de tu cuenta se cambió correctamente.<br>
                    Si no realizaste esta acción, te recomendamos restablecerla de inmediato para mantener la seguridad de tu cuenta.
                </p>
                <div class="divider"></div>

                <!-- Botón con estilos inline -->
                <a href="%s" 
                   style="display:inline-block;background-color:#2563eb;color:#ffffff;
                          font-weight:500;padding:12px 24px;border-radius:0.5rem;
                          text-decoration:none;">
                    Ir al inicio de sesión
                </a>

                <p class="footer">
                    Este mensaje fue enviado automáticamente. Por favor, no respondas a este correo.
                </p>
            </div>
        </body>
        </html>
    """.formatted(url);
    }



}
