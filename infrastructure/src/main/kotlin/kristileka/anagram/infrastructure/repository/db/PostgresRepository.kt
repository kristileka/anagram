package kristileka.anagram.infrastructure.repository.db

import kristileka.anagram.infrastructure.entity.StatefulWordEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostgresRepository : CrudRepository<StatefulWordEntity, Long> {
    fun findByValue(value: String): StatefulWordEntity?
    fun findAllByPredicate(predicate: String): List<StatefulWordEntity>
    fun findAllByValueIn(values: List<String>): List<StatefulWordEntity>
}
