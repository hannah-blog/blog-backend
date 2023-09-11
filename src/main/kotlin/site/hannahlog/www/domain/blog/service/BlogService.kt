package site.hannahlog.www.domain.blog.service

import org.springframework.stereotype.Service
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.dto.response.BlogResponse
import site.hannahlog.www.domain.blog.repository.BlogRepository
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Service
class BlogService(
    private val blogRepository: BlogRepository,
) {

    fun getList(): List<BlogListResponse> {
        return blogRepository.findAllBy()
            .map { it.toListResponse() }
    }

    fun getOne(id: Long): BlogResponse {
        return blogRepository.findByIdAndDeletedDateIsNull(id)
            .map { it.toResponse() }
            .orElseThrow { throw LogicException(ErrorStatus.NOT_EXIST_BLOG) }
    }

}
