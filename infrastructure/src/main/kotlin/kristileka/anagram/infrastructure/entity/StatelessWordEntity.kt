package kristileka.anagram.infrastructure.entity

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("word_cache")
class StatelessWordEntity {
    @Id
    var id: String? = null

    var value: String? = null

    var predicate: String? = null
}
