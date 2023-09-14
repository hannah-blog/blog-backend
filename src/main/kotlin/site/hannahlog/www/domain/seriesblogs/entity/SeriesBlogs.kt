package site.hannahlog.www.domain.seriesblogs.entity

import jakarta.persistence.*
import org.hibernate.annotations.Where
import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.series.entity.Series

@Entity
@Where(clause = "deleted_date Is NULL")
class SeriesBlogs(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    private val series: Series,

    @ManyToOne(fetch = FetchType.LAZY)
    internal val blog: Blog,
): BaseEntity()
