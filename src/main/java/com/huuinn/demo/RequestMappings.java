package com.huuinn.demo;

public interface RequestMappings {

    String HELLO = "hello";

    String HELLO_JSON = "helloJson";

    String TEST_PATH_PARAMS = "testPathParams/{name}";

    String TEST_EXCEPTION = "testException";

    String TEST_REQUEST_PARAMS = "testRequestParams";

    String TEST_CORS = "testCORS";

    String TEST_GLOBAL_CORS = "testGlobalCORS";

    String TEST_ENV = "testEnv";

    String USER = "user";

    String TEST_MULTI_DATA_SOURCE = "testMultiDataSource";

    String TEST_JPA_MULTI_DB = "testJpaMultiDB";

    String TEST_MULTI_DB = "vendor";
}
