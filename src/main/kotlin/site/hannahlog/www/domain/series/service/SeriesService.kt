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
import site.hannahlog.www.domain.seriesblogs.entity.SeriesBlogs
import site.hannahlog.www.domain.seriesblogs.repository.SeriesBlogsRepository
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Service
class SeriesService(
    private val seriesRepository: SeriesRepository,
    private val blogRepository: BlogRepository,
    private val seriesBlogsRepository: SeriesBlogsRepository,
) {

    fun findAll(): List<SeriesResponse> {
        return seriesRepository.findAll()
            .map { it.toResponse() }
    }

    @Transactional
    fun save(request: SeriesRequest): SeriesResponse {
        val saveEntity = Series.of(request)
        return seriesRepository.save(saveEntity)
            .toResponse()
    }

    @Transactional
    fun update(id: Long, request: SeriesRequest): SeriesResponse {
        val series = seriesRepository.findById(id)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_SERIES) }
        series.update(request)
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

    @Transactional
    fun addBlog(id: Long, blogId: Long) {
        val series = seriesRepository.findById(id)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_SERIES) }
        val blog = blogRepository.findById(blogId)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_BLOG) }
        val saveEntity = SeriesBlogs(series = series, blog = blog)
        seriesBlogsRepository.save(saveEntity)
    }

    @Transactional
    fun deleteBlog(id: Long, blogId: Long) {
        val series = seriesRepository.findById(id)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_SERIES) }
        val blog = blogRepository.findById(blogId)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_BLOG) }
        val seriesBlogs = seriesBlogsRepository.findBySeriesAndBlog(series, blog)
            .orElseThrow { LogicException(ErrorStatus.NOT_EXIST_SERIES_BLOGS) }
        seriesBlogs.delete()
    }

}
