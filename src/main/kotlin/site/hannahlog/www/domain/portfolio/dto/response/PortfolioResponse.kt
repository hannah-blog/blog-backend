package site.hannahlog.www.domain.portfolio.dto.response

import site.hannahlog.www.domain.portfolio.entity.Portfolio
import java.time.LocalDateTime

data class PortfolioResponse(
    val id: Long?,
    val title: String,
    val thumbnailUrl: String,
    val content: String,
    val createdDate: LocalDateTime,
    val modifiedDate: LocalDateTime,
)

fun Portfolio.toResponse() = PortfolioResponse(
    id = this.id,
    title = this.title,
    thumbnailUrl = this.thumbnailUrl,
    content = this.content,
    createdDate = this.createdDate,
    modifiedDate = this.modifiedDate,
)
