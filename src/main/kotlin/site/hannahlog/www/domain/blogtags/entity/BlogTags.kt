package site.hannahlog.www.domain.blogtags.entity

import jakarta.persistence.*
import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.tag.entity.Tag

@Entity
class BlogTags(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    private val blog: Blog,

    @ManyToOne(fetch = FetchType.EAGER)
    val tag: Tag,
)
