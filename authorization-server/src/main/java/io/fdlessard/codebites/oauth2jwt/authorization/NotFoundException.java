package io.fdlessard.codebites.oauth2jwt.authorization;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}