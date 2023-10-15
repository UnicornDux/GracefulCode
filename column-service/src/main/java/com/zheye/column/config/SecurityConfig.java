package com.zheye.column.config;

import com.zheye.column.domain.application.ZheYeUserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    @Autowired
    private ZheYeUserServiceDetail zheYeUserServiceDetail;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/user/login", "/user/create", "/static/**"))
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/images/**").permitAll()
                            .requestMatchers("/user/login", "/user/create", "/*/public/**").anonymous()
                            .anyRequest().authenticated();
                })
                // .httpBasic(Customizer.withDefaults())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer( config -> config.jwt(Customizer.withDefaults()))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new BasicAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                );
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> {
           String username = authentication.getName();
           String password = authentication.getCredentials().toString();

           // 查询数据库,比对密码
            UserDetails user = zheYeUserServiceDetail.loadUserByUsername(username);
            if (bcryptPasswordEncoder().matches(password, user.getPassword())) {
                // 密码匹配，返回一个包含用户名，密码，权限信息的Authentication对象
                return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
            }else {
                throw new BadCredentialsException("The username or password is wrong");
            }
        };
    }
}
