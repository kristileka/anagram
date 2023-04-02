package kristileka.anagram.domain.repository.cache

import kristileka.anagram.domain.dto.Word

interface StatelessWordRepository {
    fun putOnQueue(words: List<Word>)
    fun getCurrentQueue(): List<Word>
    fun cleanQueue()
}
