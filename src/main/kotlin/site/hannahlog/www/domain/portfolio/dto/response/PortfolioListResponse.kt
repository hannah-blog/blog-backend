package site.hannahlog.www.domain.portfolio.dto.response

import site.hannahlog.www.domain.portfolio.entity.Portfolio
import java.time.LocalDateTime

data class PortfolioListResponse(
    val id: Long?,
    val title: String,
    val thumbnailUrl: String,
    val createdDate: LocalDateTime,
)

fun Portfolio.toListResponse(): PortfolioListResponse = PortfolioListResponse(
    id = this.id,
    title = this.title,
    thumbnailUrl = this.thumbnailUrl,
    createdDate = this.createdDate,
)
