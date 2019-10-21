package io.fdlessard.codebites.oauth2jwt.authorization;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private UserRepository userRepository;

  public UserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User loadUserByUsername(String username) {
    Optional<User> optionalUser = userRepository.findByUsername(username);
    return optionalUser.orElseThrow(() -> new NotFoundException("Couldn't find a User with username: " + username));
  }
}

