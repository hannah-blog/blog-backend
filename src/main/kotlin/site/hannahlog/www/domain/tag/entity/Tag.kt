package site.hannahlog.www.domain.tag.entity

import jakarta.persistence.*
import org.hibernate.annotations.Where
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.tag.dto.request.TagSaveRequest

@Entity
@Where(clause = "deleted_date Is NULL")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,

    @Column(nullable = false)
    internal val name: String
): BaseEntity() {
    companion object {
        fun of(request: TagSaveRequest) = Tag(name = request.name)
    }
}
