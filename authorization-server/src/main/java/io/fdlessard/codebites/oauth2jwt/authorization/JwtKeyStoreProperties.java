package io.fdlessard.codebites.oauth2jwt.authorization;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt-key-store")
@Data
public class JwtKeyStoreProperties {

    private String file;
    private String password;
    private String key;
}
