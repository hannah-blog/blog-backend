package site.hannahlog.www.domain.model

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class BaseEntity(
    @CreatedDate
    @Column(updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(updatable = true)
    var modifiedDate: LocalDateTime = LocalDateTime.now()
) {
    var deletedDate: LocalDateTime? = null

    fun delete() {
        this.deletedDate = LocalDateTime.now()
    }
}