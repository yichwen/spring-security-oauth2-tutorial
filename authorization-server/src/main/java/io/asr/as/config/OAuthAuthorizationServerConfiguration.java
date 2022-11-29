package io.asr.as.config;

import io.asr.as.security.AuthorizationServerAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
//@EnableAuthorizationServer
@Import({AuthorizationServerEndpointsConfiguration.class, CustomizedAuthorizationServerSecurityConfiguration.class})
public class OAuthAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.afterPropertiesSet();
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProvider);
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }

    // to create client using in-memory ClientDetailsService
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client-app")
                .secret(getPasswordEncoder().encode("client-secret"))
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .redirectUris("http://localhost:8081/callback")
                .scopes("read", "write")
                .additionalInformation("nbaplayer=jordan");
    }

    // oauth/token, oauth/token_key, oauth/check_token
    // default has two authentication filter, BasicAuthenticationFilter and AnonymousAuthenticationFilter
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // this password encoder is for ClientDetailsUserDetailsService --> DaoAuthenticationProvider
        security.passwordEncoder(getPasswordEncoder())
                // oauth/check_token access
                .checkTokenAccess("permitAll()")
                .authenticationEntryPoint(new AuthorizationServerAuthenticationEntryPoint());
    }

    private PasswordEncoder getPasswordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
        return passwordEncoder;
    }

}
