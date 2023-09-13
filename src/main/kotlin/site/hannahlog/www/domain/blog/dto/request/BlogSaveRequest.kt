package site.hannahlog.www.domain.blog.dto.request

import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.blogtags.entity.BlogTags
import site.hannahlog.www.domain.tag.entity.Tag

data class BlogSaveRequest(
    val title: String,
    val content: String,
    val thumbnailUrl: String,
    val tagIds: List<Long>,
) {
    fun toEntity() = Blog(
        title = this.title,
        content = this.content,
        thumbnailUrl = this.thumbnailUrl,
    )
}