package kristileka.anagram.infrastructure.entity

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.io.Serializable

@RedisHash("word_cache", timeToLive = 350L)
class WordCacheEntity : Serializable {
    @Id
    private val id = 0

    private val value: String? = null

    @TimeToLive
    private val price: Long = 0
}