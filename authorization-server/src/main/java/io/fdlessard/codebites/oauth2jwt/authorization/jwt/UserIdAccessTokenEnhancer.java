package io.fdlessard.codebites.oauth2jwt.authorization.jwt;

import io.fdlessard.codebites.oauth2jwt.authorization.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserIdAccessTokenEnhancer implements TokenEnhancer {

  public static final String JWT_USER_ID = "user_id";

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

    Object principal = authentication.getPrincipal();
    User user = (User) principal;

    Map<String, Object> additionalInfo = new HashMap<>();
    additionalInfo.put(JWT_USER_ID, String.valueOf(user.getId()));

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

    return accessToken;
  }

}