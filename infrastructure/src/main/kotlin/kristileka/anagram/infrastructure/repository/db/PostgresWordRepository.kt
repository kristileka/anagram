package kristileka.anagram.infrastructure.repository.db

import kristileka.anagram.infrastructure.entity.WordEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostgresWordRepository : CrudRepository<WordEntity, Long> {
    fun findByValue(value: String): WordEntity?
    fun findByPredicate(predicate: String): List<WordEntity>
}