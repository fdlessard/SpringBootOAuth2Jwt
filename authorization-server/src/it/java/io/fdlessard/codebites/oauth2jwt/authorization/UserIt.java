package io.fdlessard.codebites.oauth2jwt.authorization;


import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.MEDIA_TYPE_APPLICATION_HAL_JSON;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_AUTHORIZATION;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_BEARER_STR;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_CLIENT_ID;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_CLIENT_SECRET;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIt extends BaseIt {

  @BeforeEach
  void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
        .addFilter(springSecurityFilterChain).build();
    accessToken = obtainAccessToken(TEST_CLIENT_ID, TEST_CLIENT_SECRET);
  }

  @Test
  void getUsers() throws Exception {

    mockMvc.perform(get("/users")
        .header(TEST_AUTHORIZATION, TEST_BEARER_STR + accessToken))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType(MEDIA_TYPE_APPLICATION_HAL_JSON))
        .andExpect(jsonPath("$._embedded.users", hasSize(2)))
        .andExpect(jsonPath("$._embedded.users[0].id", is(1)))
        .andExpect(jsonPath("$._embedded.users[0].password").doesNotExist());
  }

  @Test
  void getUser() throws Exception {

    mockMvc.perform(get("/users/1")
        .header(TEST_AUTHORIZATION, TEST_BEARER_STR + accessToken))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType(MEDIA_TYPE_APPLICATION_HAL_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.password").doesNotExist());

  }
}
