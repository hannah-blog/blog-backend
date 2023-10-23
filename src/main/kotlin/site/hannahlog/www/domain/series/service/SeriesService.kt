package site.hannahlog.www.domain.series.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.dto.response.toListResponse
import site.hannahlog.www.domain.blog.repository.BlogRepository
import site.hannahlog.www.domain.series.dto.request.SeriesRequest
import site.hannahlog.www.domain.series.dto.response.SeriesResponse
import site.hannahlog.www.domain.series.dto.response.toResponse
import site.hannahlog.www.domain.series.entity.Series
import site.hannahlog.www.domain.series.repository.SeriesRepository
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Service
class SeriesService(
    private val seriesRepository: SeriesRepository,
    private val blogRepository: BlogRepository,
) {

    fun findAll(): List<SeriesResponse> {
        return seriesRepository.findAll()
            .map { it.toResponse() }
    }

    fun findById(id: Long): SeriesResponse {
        return seriesRepository.findById(id)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_SERIES) }
            .toResponse()
    }

    @Transactional
    fun save(request: SeriesRequest): SeriesResponse {
        val blogs = blogRepository.findBlogsByIdIsIn(request.blogIds)
        val saveEntity = Series.of(request, blogs)
        return seriesRepository.save(saveEntity)
            .toResponse()
    }

    @Transactional
    fun update(id: Long, request: SeriesRequest): SeriesResponse {
        val blogs = blogRepository.findBlogsByIdIsIn(request.blogIds)
        val series = seriesRepository.findById(id)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_SERIES) }
        series.update(request, blogs)
        return series.toResponse()
    }

    @Transactional
    fun delete(id: Long) {
        val series = seriesRepository.findById(id)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_SERIES) }
        series.delete()
    }

    fun findBlogsById(id: Long): List<BlogListResponse> {
        val series = seriesRepository.findById(id)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_SERIES) }
        return series.blogs
            .map { it.blog.toListResponse() }
    }

}
