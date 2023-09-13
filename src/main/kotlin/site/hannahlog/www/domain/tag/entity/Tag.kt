package site.hannahlog.www.domain.tag.entity

import jakarta.persistence.*
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.tag.dto.response.TagResponse

@Entity
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,

    @Column(nullable = false)
    internal val name: String
): BaseEntity()
