package site.hannahlog.www.domain.tag.dto.response

import site.hannahlog.www.domain.tag.entity.Tag

data class TagResponse(
    val id: Long?,
    val name: String,
)

fun Tag.toResponse(): TagResponse {
    return TagResponse(
        id = this.id,
        name = this.name,
    )
}
