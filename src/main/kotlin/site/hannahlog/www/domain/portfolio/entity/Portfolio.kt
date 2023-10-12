package site.hannahlog.www.domain.portfolio.entity

import jakarta.persistence.*
import org.hibernate.annotations.Where
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.portfolio.dto.request.PortfolioRequest

@Entity
@Where(clause = "deleted_date Is NULL")
class Portfolio (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,

    @Column(nullable = false)
    internal var title: String,

    @Column(nullable = false)
    internal var thumbnailUrl: String,

    @Column(columnDefinition = "TEXT")
    internal var content: String,
): BaseEntity() {

    companion object {
        fun of(request: PortfolioRequest) = Portfolio(
            title = request.title,
            thumbnailUrl = request.thumbnailUrl,
            content = request.content,
        )
    }

    fun update(request: PortfolioRequest) {
        this.title = request.title
        this.thumbnailUrl = request.thumbnailUrl
        this.content = request.content
    }
}