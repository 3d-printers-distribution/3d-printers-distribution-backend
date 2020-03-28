package org.codevscovid19.threedprintingservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableWebSecurity
@Order(20)
public class FirebaseAuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {
    private final FirebasePreAuthenticationBearerTokenFilter tokenFilter;

    public FirebaseAuthTokenSecurityConfig(FirebasePreAuthenticationBearerTokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        final ExceptionTranslationFilter invalidAuthFilter = new ExceptionTranslationFilter(new Http401UnauthorizedEntryPoint());
        invalidAuthFilter.setAccessDeniedHandler(new Http401UnauthorizedAccessDeniedHandler());
        httpSecurity
                .antMatcher("/v1/**")
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(this.tokenFilter)
                .addFilterBefore(invalidAuthFilter, this.tokenFilter.getClass())
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new Http401UnauthorizedAccessDeniedHandler())
                .authenticationEntryPoint(new Http401UnauthorizedEntryPoint());
    }
}
