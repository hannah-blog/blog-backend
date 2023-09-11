package site.hannahlog.www.domain.blog.dto.response

import site.hannahlog.www.domain.tag.dto.response.TagResponse
import java.time.LocalDateTime

data class BlogResponse(
    val id: Long,
    val title: String,
    val thumbnailUrl: String,
    val content: String,
    val tags: List<TagResponse>,
    val createdDate: LocalDateTime,
)