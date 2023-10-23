package site.hannahlog.www.domain.series.dto.request

data class SeriesRequest(
    val title: String,
    val thumbnailUrl: String,
    val blogIds: List<Long>,
)
