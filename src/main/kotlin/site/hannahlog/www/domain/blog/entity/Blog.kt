package site.hannahlog.www.domain.blog.entity

import jakarta.persistence.*
import site.hannahlog.www.domain.blog.dto.request.BlogSaveRequest
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.dto.response.BlogResponse
import site.hannahlog.www.domain.blogtags.entity.BlogTags
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.tag.dto.response.TagResponse
import site.hannahlog.www.domain.tag.entity.Tag

@Entity
class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,

    @Column(nullable = false)
    internal val title: String,

    @Column(nullable = false)
    internal val thumbnailUrl: String,

    @Column(columnDefinition = "TEXT")
    internal val content: String,

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    internal var tags: List<BlogTags> = listOf(),
): BaseEntity() {

    companion object {
        fun of(request: BlogSaveRequest) = Blog(
            title = request.title,
            thumbnailUrl = request.thumbnailUrl,
            content = request.content,
        )
    }

    fun setUpTags(tags: List<Tag>) {
        this.tags = tags.map { BlogTags(blog = this, tag = it) }
    }

}
