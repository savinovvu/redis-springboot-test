package ru.inbox.savinov_vu.test_helpers.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.inbox.savinov_vu.test_helpers.config.redis.EmbeddedRedis;
import ru.inbox.savinov_vu.test_helpers.config.redis.RedisSettings;



@TestConfiguration
@Import({
        EmbeddedRedis.class,
        RedisSettings.class,
})
@ComponentScan("ru.inbox.savinov_vu.test_helpers.init")
public class InitConfig {

}
