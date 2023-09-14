package site.hannahlog.www.domain.series.entity

import jakarta.persistence.*
import org.hibernate.annotations.Where
import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.series.dto.request.SeriesRequest
import site.hannahlog.www.domain.seriesblogs.entity.SeriesBlogs

@Entity
@Where(clause = "deleted_date Is NULL")
class Series(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,

    @Column(nullable = false)
    internal var title: String,

    @Column(nullable = false)
    internal var thumbnailUrl: String,

    @OneToMany(mappedBy = "series", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    internal val blogs: List<SeriesBlogs>
): BaseEntity() {

    companion object {
        fun of(request: SeriesRequest) = Series(
            title = request.title,
            thumbnailUrl = request.thumbnailUrl,
            blogs = emptyList()
        )
    }

    fun update(request: SeriesRequest) {
        this.title = request.title
        this.thumbnailUrl = request.thumbnailUrl
    }

}
