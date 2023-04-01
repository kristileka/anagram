package kristileka.anagram.domain.service.anagram

import kristileka.anagram.domain.dto.EvaluationResult
import kristileka.anagram.domain.dto.Word

interface AnagramService {
    suspend fun evaluateAnagram(vararg values: String): EvaluationResult
    fun evaluateAnagram(words: List<String>, takeFirst: Boolean = false): Pair<List<EvaluationResult>, String>
    fun insertWord(value: String): Boolean
    suspend fun searchForAnagram(value: String): List<Word>
}