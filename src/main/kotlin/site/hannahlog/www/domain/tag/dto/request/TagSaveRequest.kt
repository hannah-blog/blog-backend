package site.hannahlog.www.domain.tag.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class TagSaveRequest(
    @JsonProperty("name")
    val name: String
)
