package site.hannahlog.www.domain.blog.entity

import jakarta.persistence.*
import org.hibernate.annotations.Where
import site.hannahlog.www.domain.blog.dto.request.BlogRequest
import site.hannahlog.www.domain.blogtags.entity.BlogTags
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.tag.entity.Tag

@Entity
@Where(clause = "deleted_date Is NULL")
class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,

    @Column(nullable = false)
    internal var title: String,

    @Column(nullable = false)
    internal var thumbnailUrl: String,

    @Column(columnDefinition = "TEXT")
    internal var content: String,

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    internal val tags: MutableList<BlogTags> = mutableListOf(),
): BaseEntity() {

    companion object {
        fun of(request: BlogRequest, tags: List<Tag>): Blog {
            val blog = Blog(
                title = request.title,
                thumbnailUrl = request.thumbnailUrl,
                content = request.content,
            )
            blog.setUpTags(tags)
            return blog
        }
    }

    fun setUpTags(tags: List<Tag>) {
        tags.map { this.tags.add(BlogTags(blog = this, tag = it)) }
    }

    fun update(request: BlogRequest, tags: List<Tag>) {
        this.title = request.title
        this.thumbnailUrl = request.thumbnailUrl
        this.content = request.content
        this.tags.clear()
        this.setUpTags(tags)
    }

}
