package kristileka.anagram.domain.repository

import kristileka.anagram.domain.dto.Word

interface WordRepository {
    fun save(word: Word): Word
    fun findWordById(id: Long): Word
    fun findWordByValue(value: String): Word
    fun filterByPredicate(predicate: String): List<Word>
    fun upRateWord(word: String): Word
    fun downRateWord(word: String): String
}