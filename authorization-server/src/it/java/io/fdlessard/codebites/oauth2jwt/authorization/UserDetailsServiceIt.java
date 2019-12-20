package io.fdlessard.codebites.oauth2jwt.authorization;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("integration")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserDetailsServiceIt {

    @Autowired
    private UserService userService;

    @Test
    void loadUserByUsername() {
        User user = userService.loadUserByUsername(TEST_USERNAME);
        assertNotNull(user);
        assertEquals(0, user.getId());
    }
}