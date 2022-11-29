package io.tao.rs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CustomizedResourceServerTokenServices implements ResourceServerTokenServices {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenConverter accessTokenConverter;

    private static final String CHECK_TOKEN_URL = "http://localhost:9000/oauth/check_token?token=";

    @Override
    public OAuth2Authentication loadAuthentication(String token) throws AuthenticationException, InvalidTokenException {
        Map<String, Object> response = getResponse(token);
        return accessTokenConverter.extractAuthentication(response);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String token) {
        Map<String, Object> response = getResponse(token);
        return accessTokenConverter.extractAccessToken(token, response);
    }

    private Map<String, Object> getResponse(String token) {
        String url = CHECK_TOKEN_URL + token;
        return (Map<String, Object>) restTemplate.getForObject(url, Map.class);
    }

}
