package site.hannahlog.www.domain.blog.dto.request

import site.hannahlog.www.domain.blog.entity.Blog

data class BlogRequest(
    val title: String,
    val content: String,
    val thumbnailUrl: String,
    val tagIds: List<Long>,
)
