package site.hannahlog.www.domain.blog.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import site.hannahlog.www.domain.blog.dto.request.BlogRequest
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.dto.response.BlogResponse
import site.hannahlog.www.domain.blog.dto.response.toListResponse
import site.hannahlog.www.domain.blog.dto.response.toResponse
import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.blog.repository.BlogRepository
import site.hannahlog.www.domain.tag.repository.TagRepository
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Service
class BlogService(
    private val blogRepository: BlogRepository,
    private val tagRepository: TagRepository,
) {

    fun getList(): List<BlogListResponse> {
        return blogRepository.findAllBy()
            .map { it.toListResponse() }
    }

    fun getOne(id: Long): BlogResponse {
        return blogRepository.findBlogById(id)
            .map { it.toResponse() }
            .orElseThrow { throw LogicException(ErrorStatus.NOT_EXIST_BLOG) }
    }

    @Transactional
    fun saveBlog(request: BlogRequest): BlogResponse {
        val tags = tagRepository.findTagsByIdIsIn(request.tagIds)
        val saveEntity = Blog.of(request, tags)
        return blogRepository.save(saveEntity)
            .toResponse()
    }

    @Transactional
    fun updateBlog(id: Long, request: BlogRequest): BlogResponse {
        val tags = tagRepository.findTagsByIdIsIn(request.tagIds)
        val blog = blogRepository.findBlogById(id)
            .orElseThrow { throw LogicException(ErrorStatus.NOT_EXIST_BLOG) }
        blog.update(request, tags)
        return blog.toResponse()
    }

    @Transactional
    fun deleteBlog(id: Long) {
        blogRepository.findById(id)
            .ifPresent { blog -> blog.delete() }
    }

}
