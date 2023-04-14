package com.example.webexam.config;

import com.example.webexam.model.enums.UserRoleEnum;
import com.example.webexam.repository.UserRepository;
import com.example.webexam.service.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http,
      SecurityContextRepository securityContextRepository) throws Exception {
    http.
        authorizeHttpRequests().
          requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
          requestMatchers("/", "/users/login", "/users/register", "/users/login-error", "/about-us", "/find-my-doc").permitAll().
          requestMatchers("/pages/moderators").hasRole(UserRoleEnum.MODERATOR.name()).
          requestMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name()).
        anyRequest().authenticated().
          and().
            formLogin().
              loginPage("/users/login").
              usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
              passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
              defaultSuccessUrl("/").
              failureForwardUrl("/users/login-error").
          and().logout().
            logoutUrl("/users/logout").
            logoutSuccessUrl("/").
            invalidateHttpSession(true).
          and().
            securityContext().
            securityContextRepository(securityContextRepository);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return new ApplicationUserDetailsService(userRepository);
  }

  @Bean
  public SecurityContextRepository securityContextRepository() {
    return new DelegatingSecurityContextRepository(
        new RequestAttributeSecurityContextRepository(),
        new HttpSessionSecurityContextRepository()
    );
  }

}