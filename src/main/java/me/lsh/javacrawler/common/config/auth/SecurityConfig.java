package me.lsh.javacrawler.common.config.auth;


import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.domain.member.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/", "/competitions/**", "/moims/**", "/login/**",
                "/assets/**").permitAll()
            .antMatchers("/profile/**", "/api/v1/**").hasRole(Role.GUEST.name())
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .oauth2Login()
            .successHandler(successHandler)
            .userInfoEndpoint()
            .userService(customOAuth2UserService);
        return http.build();
    }
}
