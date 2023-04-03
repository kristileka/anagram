package kristileka.anagram.infrastructure.config

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RedisConfigurationTest {

    private val redisConfig = RedisConfiguration()

    @Test
    fun `test redis jedis configuration`() {
        val result = redisConfig.getRedisHostName()
        assertEquals(result, "127.0.0.1")
    }

}