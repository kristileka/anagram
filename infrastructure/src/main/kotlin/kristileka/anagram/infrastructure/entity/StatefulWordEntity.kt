package kristileka.anagram.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import kristileka.anagram.domain.dto.Word
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime


@Entity(name = "stateful_word")
@Table(
    indexes = [Index(
        name = "letter_count_index", columnList = "letter_count", unique = false
    )]
)
class StatefulWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "value", unique = true)
    var value: String? = null

    @Column(name = "predicate")
    var predicate: String? = null

    @Column(name = "letter_count")
    var letterCount: Int? = null

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null

    companion object {
        fun Word.fromDomain(): StatefulWordEntity {
            return StatefulWordEntity().also {
                it.value = this.value
                it.predicate = this.predicate
                it.letterCount = this.value.length
            }
        }

        fun StatefulWordEntity.toDomain(): Word {
            return Word(
                this.value!!,
                this.predicate!!
            )
        }
    }
}