package kristileka.anagram.domain.service.anagram

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kristileka.anagram.domain.dto.AnagramCouldNotBeFound
import kristileka.anagram.domain.dto.EvaluationResult
import kristileka.anagram.domain.dto.Word
import kristileka.anagram.domain.dto.WordAlreadyRegistered
import kristileka.anagram.domain.extensions.Extensions.getPredicate
import kristileka.anagram.domain.repository.cache.StatelessWordRepository
import kristileka.anagram.domain.repository.db.StatefulWordRepository
import org.springframework.stereotype.Service

@Service
class AnagramServiceImpl(
    val backgroundScope: CoroutineScope,
    val statefulWordRepository: StatefulWordRepository,
    val statelessWordRepository: StatelessWordRepository,
) : AnagramService {
    override suspend fun evaluateAnagram(vararg values: String): EvaluationResult = coroutineScope {
        val word1 = Word(
            values[0],
            values[0].getPredicate(),
        )
        val word2 = Word(
            values[1],
            values[1].getPredicate(),
        )
        val result = word1.predicate == word2.predicate
        registerStatelessWords(
            listOf(
                word1,
                word2,
            ),
        )
        return@coroutineScope EvaluationResult(
            word1.value,
            word2.value,
            result,
        )
    }

    override suspend fun evaluateAnagram(
        listWords: List<String>,
    ): Pair<List<EvaluationResult>, String> = coroutineScope {
        val words = listWords.map {
            Word(it, it.getPredicate())
        }
        registerStatelessWords(
            words,
        )
        return@coroutineScope evaluateAnagramResulted(words = words)
    }

    private fun evaluateAnagramResulted(
        words: List<Word>,
    ): Pair<List<EvaluationResult>, String> {
        val atMostPredicate = words.groupingBy { it.predicate }.eachCount().maxByOrNull { it.value }?.key
        val selectedAnagram = words.first { it.predicate == atMostPredicate }.value
        return Pair(
            words.map {
                EvaluationResult(
                    it.value,
                    selectedAnagram,
                    it.predicate == atMostPredicate,
                )
            },
            selectedAnagram,
        )
    }

    override fun insertWord(value: String): Boolean {
        val statefulWord = statefulWordRepository.findWordByValue(value)
        if (statefulWord != null) throw WordAlreadyRegistered()
        return statefulWordRepository.save(Word(value, value.getPredicate())) != null
    }

    override fun searchForAnagram(value: String): List<String> {
        val statefulWordsByPredicate = statefulWordRepository.filterByPredicate(
            value.getPredicate(),
        )
        if (statefulWordsByPredicate.isEmpty()) throw AnagramCouldNotBeFound()
        return statefulWordsByPredicate.map {
            it.value
        }
    }

    suspend fun registerStatelessWords(words: List<Word>) {
        backgroundScope.launch {
            statelessWordRepository.putOnQueue(
                words,
            )
        }
    }
}
