package com.ecommerce.ecommerce.config.security;

import com.ecommerce.ecommerce.config.security.filter.JwtAuthenticationFilter;
import com.ecommerce.ecommerce.config.security.handler.CustomAccessDeniedHandler;
import com.ecommerce.ecommerce.config.security.handler.CustomAuthenticationEntryPoint;
import com.ecommerce.ecommerce.util.RolePermissionEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public HttpSecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, CustomAccessDeniedHandler customAccessDeniedHandler){
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sesMagConfig -> sesMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authReqConfig -> {
                    buildRequestMatchers(authReqConfig);
                })
                .exceptionHandling(exceptionConfig -> {
                    exceptionConfig.authenticationEntryPoint(customAuthenticationEntryPoint);
                    exceptionConfig.accessDeniedHandler(customAccessDeniedHandler);
                })
                .build();
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig){

        //Autorización Familias
        authReqConfig.requestMatchers(HttpMethod.GET, "/families").hasAuthority(RolePermissionEnum.READ_FAMILIES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/families/{id}").hasAuthority(RolePermissionEnum.READ_FAMILY.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/families").hasAuthority(RolePermissionEnum.CREATE_FAMILY.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/families/{id}").hasAuthority(RolePermissionEnum.UPDATE_FAMILY.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/families/{id}").hasAuthority(RolePermissionEnum.DELETE_FAMILY.name());
        //Autorización Categorias
        authReqConfig.requestMatchers(HttpMethod.GET, "/categories").hasAuthority(RolePermissionEnum.READ_CATEGORIES.name());
        authReqConfig.requestMatchers(HttpMethod.GET, "/categories/{id}").hasAuthority(RolePermissionEnum.DELETE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.POST, "/categories").hasAuthority(RolePermissionEnum.DELETE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.PUT, "/categories/{id}").hasAuthority(RolePermissionEnum.DELETE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/categories/{id}").hasAuthority(RolePermissionEnum.DELETE_CATEGORY.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/profile").hasAuthority(RolePermissionEnum.READ_MY_PROFILE.name());

        //Autorizacion enpoint publicos
        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();


        authReqConfig.anyRequest().authenticated();
    }
}
