package site.hannahlog.www.domain.series.entity

import jakarta.persistence.*
import org.hibernate.annotations.Where
import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.seriesblogs.entity.SeriesBlogs

@Entity
@Where(clause = "deleted_date Is NULL")
class Series(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(nullable = false)
    private val title: String,

    @Column(nullable = false)
    private val thumbnailUrl: String,

    @OneToMany(mappedBy = "series", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    private val blogs: List<SeriesBlogs>
): BaseEntity()
