package io.fdlessard.codebites.oauth2jwt.authorization;

public class TestConstants {

    public static final String TEST_ID = "id";
    public static final String TEST_VERSION = "version";

    public static final String TEST_HTTP_LOCALHOST = "http://localhost:";
    public static final String TEST_EMAIL = "test@test.com";
    public static final String TEST_URL = "www.test.com";

    public static String MEDIA_TYPE_APPLICATION_HAL_JSON = "application/hal+json";

    // Test Authentication, Authorization, OAuth2
    public static final String TEST_USERNAME_STR = "username";
    public static final String TEST_USERNAME = "test@email.com";
    public static final String TEST_PASSWORD_STR = "password";
    public static final String TEST_CLIENT_ID = "client";
    public static final String TEST_CLIENT_SECRET = "secret";
    public static final String TEST_AUTHORIZATION = "Authorization";
    public static final String TEST_BEARER_STR = "Bearer ";
    public static final String TEST_GRANT_TYPE_STR = "grant_type";
    public static final String TEST_SCOPE_STR = "scope";
    public static final String TEST_SCOPE_ALL = "all";
    public static final String TEST_ACCESS_TOKEN = "access_token";
    public static final String[] TEST_SCOPES = {"testScope1", "testScope2"};
    public static final String[] TEST_AUTHORIZATION_GRANTS = {"grant1", "grant2"};

    // Test Error Response
    public static final String TEST_ERROR_TYPE = "error-type";
    public static final String TEST_ERROR_CODE = "error-code";
    public static final String TEST_ERROR_MESSAGE = "error-message";
    public static final String TEST_ERROR_FIELD = "error-field";
    public static final String TEST_ANY = "any";
    public static final String TEST_FIELD_1 = "field1";
    public static final String TEST_FIELD_2 = "field2";
    public static final String TEST_CONSTRAINT_1_MESSAGE = "Constraint 1 Message";
    public static final String TEST_CONSTRAINT_2_MESSAGE = "Constraint 2 Message";
    public static final String TEST_EXCEPTION_MSG = "Generic  Exception message";

    // Test Dates, timestamps
    public static final String TEST_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TEST_DATE_FORMAT = "yyyy-MM-dd";
    public static final String TEST_DATE_STR = "2019-09-01";
    public static final String TEST_DATE_TIME_STR = "2019-09-01T12:00:00-05:00";

    public static final String TEST_ADDRESS = "address";

    public static final String TEST_DESCRIPTION = "description";
    public static final String TEST_CODE = "code";
    public static final String TEST_NAME = "name";
    public static final String TEST_TYPE = "type";

    // private constructor since this is a constant class
    private TestConstants() {
    }

}
