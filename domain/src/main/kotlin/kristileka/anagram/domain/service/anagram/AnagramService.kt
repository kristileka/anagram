package kristileka.anagram.domain.service.anagram

import kristileka.anagram.domain.dto.EvaluationResult

interface AnagramService {
    suspend fun evaluateAnagram(vararg values: String): EvaluationResult
    suspend fun evaluateAnagram(listWords: List<String>): Pair<List<EvaluationResult>, String>
    fun insertWord(value: String): Boolean
    fun searchForAnagram(value: String): List<String>
}
