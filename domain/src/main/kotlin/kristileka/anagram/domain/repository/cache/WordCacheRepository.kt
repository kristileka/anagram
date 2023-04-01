package kristileka.anagram.domain.repository.cache

import kristileka.anagram.domain.dto.Word

interface WordCacheRepository {
    fun putOnQueue(words: List<Word>)
    fun getCurrentQueue(): List<Word>
    fun cleanQueue(): Boolean
}