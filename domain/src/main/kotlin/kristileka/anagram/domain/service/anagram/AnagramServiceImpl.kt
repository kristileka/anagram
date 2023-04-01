package kristileka.anagram.domain.service.anagram

import kotlinx.coroutines.*
import kristileka.anagram.domain.dto.EvaluationResult
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.dto.WordAlreadyRegistered
import kristileka.anagram.domain.extensions.Extensions.getPredicate
import kristileka.anagram.domain.repository.cache.StatelessWordRepository
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import org.springframework.stereotype.Service

@Service
class AnagramServiceImpl(
    var backgroundScope: CoroutineScope,
    var statefulWordRepository: StatefulWordRepository,
    var statelessWordRepository: StatelessWordRepository
) : AnagramService {
    override suspend fun evaluateAnagram(vararg values: String): EvaluationResult = coroutineScope {
        backgroundScope.launch {
            statefulWordRepository.save(Word(value = values[0], values[0].getPredicate()))
        }
        return@coroutineScope EvaluationResult(values[0], values[1], values.map {
            it.getPredicate()
        }.distinct().size == 1)
    }

    override fun evaluateAnagram(words: List<String>, takeFirst: Boolean): Pair<List<EvaluationResult>, String> {
        return evaluateAnagramResulted(words = words)
    }

    fun evaluateAnagramResulted(
        words: List<String>,
    ): Pair<List<EvaluationResult>, String> {
        val wordsToPredicate = words.map { it to it.getPredicate() }
        val atMostPredicate = wordsToPredicate.groupingBy { it.second }.eachCount()
            .maxByOrNull { it.value }?.key
        val selectedAnagram = wordsToPredicate.first { it.second == atMostPredicate }.first
        return Pair(wordsToPredicate.map {
            EvaluationResult(
                it.first,
                selectedAnagram,
                it.second == atMostPredicate
            )
        }, selectedAnagram)
    }

    override fun insertWord(value: String): Boolean {
        val savedWord = statefulWordRepository.findWordByValue(value)
        if (savedWord != null)
            throw WordAlreadyRegistered()
        return statefulWordRepository.save(Word(value, value.getPredicate()))
    }


    override suspend fun searchForAnagram(value: String): List<Word> {
        TODO("Not yet implemented")
    }
}