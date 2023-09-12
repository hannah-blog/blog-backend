package site.hannahlog.www.domain.blog.entity

import jakarta.persistence.*
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.dto.response.BlogResponse
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.tag.entity.Tag

@Entity
class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(nullable = false)
    private val title: String,

    @Column(nullable = false)
    private val thumbnailUrl: String,

    @Column(columnDefinition = "TEXT")
    private val content: String,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    private val tags: List<Tag>,
): BaseEntity() {

    fun toListResponse() = BlogListResponse(
        id = this.id,
        title = this.title,
        thumbnailUrl = this.thumbnailUrl,
        tags = this.tags.map { it.toResponse() },
        createdDate = this.createdDate,
    )

    fun toResponse() = BlogResponse(
        id = this.id,
        title = this.title,
        thumbnailUrl = this.thumbnailUrl,
        content = this.content,
        tags = this.tags.map { it.toResponse() },
        createdDate = this.createdDate,
    )

}
