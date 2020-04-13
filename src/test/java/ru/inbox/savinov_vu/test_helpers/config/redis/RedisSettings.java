package ru.inbox.savinov_vu.test_helpers.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;



@TestConfiguration
public class RedisSettings {

    private final String host;

    private final int port;


    public RedisSettings(@Value("${spring.redis.host}") String host,
                           @Value("${spring.redis.port}") int port) {
        this.host = host;
        this.port = port;
    }


    public String getHost() {
        return host;
    }


    public int getPort() {
        return port;
    }

}
