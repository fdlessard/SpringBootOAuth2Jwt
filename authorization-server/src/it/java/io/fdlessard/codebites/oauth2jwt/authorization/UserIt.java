package io.fdlessard.codebites.oauth2jwt.authorization;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIt {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;


    @BeforeEach
    void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    void getUsers() throws Exception {

        mockMvc.perform(get("/users").secure( true )
                .with(httpBasic(TEST_USERNAME, TEST_PASSWORD_STR)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MEDIA_TYPE_APPLICATION_HAL_JSON))
                .andExpect(jsonPath("$._embedded.users", hasSize(1)))
                .andExpect(jsonPath("$._embedded.users[0].username", is(TEST_USERNAME)))
                .andExpect(jsonPath("$._embedded.users[0].password").doesNotExist());
    }

    @Test
    void getUser() throws Exception {

        mockMvc.perform(get("/users/0").secure( true )
                .with(httpBasic(TEST_USERNAME, TEST_PASSWORD_STR)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MEDIA_TYPE_APPLICATION_HAL_JSON))
                .andExpect(jsonPath("$.username", is(TEST_USERNAME)))
                .andExpect(jsonPath("$.password").doesNotExist());

    }
}
