package kristileka.anagram.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime


@Entity
@Table(
    indexes = [Index(
        name = "letter_count_index",
        columnList = "letter_count",
        unique = false
    ), Index(name = "rating_index", columnList = "rating", unique = false)]
)
class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "value", unique = true)
    var value: String? = null

    @Column(name = "predicate")
    var predicate: String? = null

    @Column(name = "letter_count")
    var letterCount: Int? = null

    @Column(name = "rating")
    var rating: Int? = null

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null
}