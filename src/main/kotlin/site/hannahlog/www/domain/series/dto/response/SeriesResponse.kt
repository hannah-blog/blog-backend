package site.hannahlog.www.domain.series.dto.response

import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.dto.response.toListResponse
import site.hannahlog.www.domain.series.entity.Series
import java.time.LocalDateTime

data class SeriesResponse(
    val id: Long?,
    val title: String,
    val thumbnailUrl: String,
    val blogs: List<BlogListResponse>,
    val createdDate: LocalDateTime
)

fun Series.toResponse() = SeriesResponse(
    id = this.id,
    title = this.title,
    thumbnailUrl = this.thumbnailUrl,
    blogs = this.blogs.map { it.blog.toListResponse() },
    createdDate = this.createdDate
)
