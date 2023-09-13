package site.hannahlog.www.domain.blog.dto.response

import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.tag.dto.response.TagResponse
import site.hannahlog.www.domain.tag.dto.response.toResponse
import java.time.LocalDateTime

data class BlogResponse(
    val id: Long?,
    val title: String,
    val thumbnailUrl: String,
    val content: String,
    val tags: List<TagResponse>,
    val createdDate: LocalDateTime,
)

fun Blog.toResponse() = BlogResponse(
    id = this.id,
    title = this.title,
    thumbnailUrl = this.thumbnailUrl,
    content = this.content,
    tags = this.tags.map { it.tag.toResponse() },
    createdDate = this.createdDate,
)
