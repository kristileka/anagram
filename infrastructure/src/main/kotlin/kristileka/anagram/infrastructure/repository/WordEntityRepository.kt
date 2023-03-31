package kristileka.anagram.infrastructure.repository

import kristileka.anagram.infrastructure.entity.WordEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WordEntityRepository : CrudRepository<WordEntity, Long> {
}