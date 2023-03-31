package kristileka.anagram.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["kristileka.anagram"])
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["kristileka.anagram.*"])
@EntityScan("kristileka.anagram.*")
class AnagramApplication

fun main(args: Array<String>) {
    runApplication<AnagramApplication>(*args)
}
