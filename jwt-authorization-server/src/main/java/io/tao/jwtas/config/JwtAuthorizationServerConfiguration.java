package io.tao.jwtas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

/**
 * the security configuration only applies to /oauth/token, /oauth/check_token, /oauth/token_key
 */
@Configuration
@EnableAuthorizationServer
public class JwtAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .authenticationEntryPoint(new OAuth2AuthenticationEntryPoint())
//                .accessDeniedHandler()
                .passwordEncoder(passwordEncoder)  // password encoder for ClientDetailsService
                .tokenKeyAccess("permitAll()");    // open to public to get public key
    }

}
