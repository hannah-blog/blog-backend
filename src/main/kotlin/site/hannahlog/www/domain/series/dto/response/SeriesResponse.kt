package site.hannahlog.www.domain.series.dto.response

import site.hannahlog.www.domain.series.entity.Series
import java.time.LocalDateTime

data class SeriesResponse(
    val id: Long?,
    val title: String,
    val thumbnailUrl: String,
    val createdDate: LocalDateTime
)

fun Series.toResponse() = SeriesResponse(
    id = this.id,
    title = this.title,
    thumbnailUrl = this.thumbnailUrl,
    createdDate = this.createdDate
)
