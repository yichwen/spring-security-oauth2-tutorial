package io.asr.as.config;

import io.asr.as.security.AuthorizationServerAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;

@Configuration
@Order(0)
@Import({ClientDetailsServiceConfiguration.class, AuthorizationServerEndpointsConfiguration.class})
public class CustomizedAuthorizationServerSecurityConfiguration extends AuthorizationServerSecurityConfiguration {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.httpBasic().authenticationEntryPoint(new AuthorizationServerAuthenticationEntryPoint());
    }

}
