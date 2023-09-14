package site.hannahlog.www.domain.seriesblogs.entity

import jakarta.persistence.*
import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.series.entity.Series

@Entity
class SeriesBlogs(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    private val series: Series,

    @ManyToOne(fetch = FetchType.LAZY)
    private val blog: Blog,
)