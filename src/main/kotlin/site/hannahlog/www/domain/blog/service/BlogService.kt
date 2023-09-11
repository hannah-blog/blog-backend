package site.hannahlog.www.domain.blog.service

import org.springframework.stereotype.Service
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.repository.BlogRepository

@Service
class BlogService(
    private val blogRepository: BlogRepository,
) {

    fun getList(): List<BlogListResponse> {
        return blogRepository.findAllBy()
            .map { it.toListResponse() }
    }

}