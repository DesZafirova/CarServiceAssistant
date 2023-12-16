package com.vehicleassistancediary.config;

import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import com.vehicleassistancediary.repository.UserRepository;
import com.vehicleassistancediary.service.CarServiceAssistantUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${carservice.remember.me.key}") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorizeRequest ->
                                authorizeRequest

                                        .requestMatchers("/js/**", "/css/**", "/images/**", "/lib/**", "/scss/**").permitAll()
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .requestMatchers("/", "/users/login", "/registration/**", "/services/login", "/users/login-error", "/services/login-error", "about").permitAll()
                                        .requestMatchers("/garage/all").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/car/**").permitAll()
                                        .requestMatchers("/error").permitAll()
                                        .requestMatchers( "/repair/**", "/taxes/**").hasRole(UserRoleEnum.USER.name())
                                        .requestMatchers("/service/**").hasRole(UserRoleEnum.SERVICE.name())
                                        .requestMatchers("/service").hasRole(UserRoleEnum.ADMIN.name())

                                        // all other requests are authenticated.
                                        .anyRequest().authenticated()


                ).formLogin(
                        formLogin -> {
                            formLogin.loginPage("/users/login")
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/", true)
                                    .failureUrl("/users/login-error");
                        }
                ).logout(
                        logout -> {
                            logout.logoutUrl("/users/logout")
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true);
                        }
                ).rememberMe(
                        rememberMe -> {
                            rememberMe.key(rememberMeKey)
                                    .rememberMeParameter("rememberme")
                                    .rememberMeCookieName("rememberme");
                        }
                )
        ;
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CarServiceAssistantUserDetailService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }


}
