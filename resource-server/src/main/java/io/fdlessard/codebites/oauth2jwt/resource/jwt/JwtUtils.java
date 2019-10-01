package io.fdlessard.codebites.oauth2jwt.resource.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class JwtUtils {

  public static final String JWT_USER_ID = "user_id";

  // private constructor since this is a utils class
  private JwtUtils() {
  }

  // Maybe find a cleaner way
  public static long extractUserId(Authentication authentication) {

    OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();

    Object object = ((Map<String, Object>) auth2AuthenticationDetails.getDecodedDetails()).get(JWT_USER_ID);

    return Long.valueOf((String) object);
  }
}
