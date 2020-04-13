package ru.inbox.savinov_vu.test_helpers.config.redis;

import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static java.util.Objects.nonNull;



@TestConfiguration
public class EmbeddedRedis {

    private RedisServer redisServer;


    public EmbeddedRedis(RedisSettings settings) {

        this.redisServer = RedisServer.builder()
                .port(settings.getPort())
                .setting("maxmemory 128M")
                .build();

    }


    @PostConstruct
    public void postConstruct() {
        try {
            redisServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @PreDestroy
    public void preDestroy() {
        if (nonNull(redisServer)) {
            redisServer.stop();
        }
    }
}
