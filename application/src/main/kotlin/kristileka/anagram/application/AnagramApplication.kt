package kristileka.anagram.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["kristileka.anagram"])
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["kristileka.anagram.infrastructure"])
@EnableRedisRepositories(basePackages = ["kristileka.anagram.infrastructure"])
@EntityScan("kristileka.anagram.*")
@EnableScheduling
class AnagramApplication

fun main(args: Array<String>) {
    runApplication<AnagramApplication>(*args)
}
