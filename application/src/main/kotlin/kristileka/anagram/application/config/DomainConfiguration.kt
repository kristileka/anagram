package kristileka.anagram.application.config

import kristileka.anagram.domain.repository.WordRepository
import kristileka.anagram.domain.service.DomainWordService
import kristileka.anagram.domain.service.WordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainConfiguration {

    @Autowired
    lateinit var wordRepository: WordRepository

    @Bean
    fun provideWordService(): WordService {
        return DomainWordService(wordRepository)
    }
}