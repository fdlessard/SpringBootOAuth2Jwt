package io.fdlessard.codebites.oauth2jwt.authorization;

import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_ACCESS_TOKEN;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_CLIENT_ID;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_CLIENT_SECRET;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_GRANT_TYPE_STR;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_PASSWORD_STR;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_SCOPE_ALL;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_SCOPE_STR;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_USERNAME;
import static io.fdlessard.codebites.oauth2jwt.authorization.TestConstants.TEST_USERNAME_STR;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

public abstract class BaseIt {

  @Autowired
  protected WebApplicationContext wac;

  @Autowired
  protected FilterChainProxy springSecurityFilterChain;

  protected MockMvc mockMvc;

  protected String accessToken;

  protected String obtainAccessToken(String username, String password) throws Exception {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(TEST_GRANT_TYPE_STR, TEST_PASSWORD_STR);
    params.add(TEST_USERNAME_STR, TEST_USERNAME);
    params.add(TEST_PASSWORD_STR, TEST_PASSWORD_STR);
    params.add(TEST_SCOPE_STR, TEST_SCOPE_ALL);

    ResultActions result
        = mockMvc.perform(post("/oauth/token")
        .params(params)
        .params(params)
        .with(httpBasic(TEST_CLIENT_ID, TEST_CLIENT_SECRET))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));

    String resultString = result.andReturn().getResponse().getContentAsString();

    JacksonJsonParser jsonParser = new JacksonJsonParser();

    return jsonParser.parseMap(resultString).get(TEST_ACCESS_TOKEN).toString();
  }
}
