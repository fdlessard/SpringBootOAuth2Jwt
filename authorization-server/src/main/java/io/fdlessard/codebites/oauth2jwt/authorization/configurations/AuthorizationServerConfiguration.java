package io.fdlessard.codebites.oauth2jwt.authorization.configurations;

import io.fdlessard.codebites.oauth2jwt.authorization.JwtKeyStoreProperties;
import io.fdlessard.codebites.oauth2jwt.authorization.OAuth2ClientProperties;
import io.fdlessard.codebites.oauth2jwt.authorization.UserService;
import io.fdlessard.codebites.oauth2jwt.authorization.jwt.UserIdAccessTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter));

        endpoints.authenticationManager(authenticationManager)
                .tokenEnhancer(tokenEnhancerChain)
                .userDetailsService(userService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(oAuth2ClientProperties.getClientId())
                .secret(oAuth2ClientProperties.getClientSecret())
                .authorizedGrantTypes(oAuth2ClientProperties.getAuthorizedGrantTypes())
                .scopes(oAuth2ClientProperties.getScopes())
                .accessTokenValiditySeconds(oAuth2ClientProperties.getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(oAuth2ClientProperties.getRefreshTokenValiditySeconds());
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new UserIdAccessTokenEnhancer();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(JwtKeyStoreProperties jwtKeyStoreProperties) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource(jwtKeyStoreProperties.getFile()), jwtKeyStoreProperties.getPassword().toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(jwtKeyStoreProperties.getKey()));
        return converter;
    }

}
