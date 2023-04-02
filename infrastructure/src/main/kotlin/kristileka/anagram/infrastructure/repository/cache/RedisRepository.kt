package kristileka.anagram.infrastructure.repository.cache

import kristileka.anagram.infrastructure.entity.StatelessWordEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RedisRepository : CrudRepository<StatelessWordEntity, String>
