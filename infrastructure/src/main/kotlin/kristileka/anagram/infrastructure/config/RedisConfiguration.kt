package kristileka.anagram.infrastructure.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory


@Configuration
class RedisConfiguration {

    fun getRedisHostName(): String {
        val redisHost = System.getenv("REDIS_HOST")
        return redisHost ?: "127.0.0.1"
    }


    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val configuration = RedisStandaloneConfiguration()
        configuration.hostName = getRedisHostName()
        return JedisConnectionFactory(configuration)
    }
}