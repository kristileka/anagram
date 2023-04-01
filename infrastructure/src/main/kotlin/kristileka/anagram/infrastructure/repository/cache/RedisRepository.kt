package kristileka.anagram.infrastructure.repository.cache

import kristileka.anagram.infrastructure.entity.StatelessWordEntity
import org.springframework.data.repository.CrudRepository

interface RedisRepository : CrudRepository<StatelessWordEntity, Long>