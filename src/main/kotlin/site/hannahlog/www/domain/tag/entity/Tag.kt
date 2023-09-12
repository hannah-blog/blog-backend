package site.hannahlog.www.domain.tag.entity

import jakarta.persistence.*
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.tag.dto.response.TagResponse

@Entity
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(nullable = false)
    private val name: String
): BaseEntity() {

    fun toResponse(): TagResponse {
        return TagResponse(
            id = this.id,
            name = this.name,
        )
    }

}
