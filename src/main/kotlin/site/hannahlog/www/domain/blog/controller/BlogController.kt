package site.hannahlog.www.domain.blog.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.service.BlogService
import site.hannahlog.www.global.common.response.ApiResponse.Success
import site.hannahlog.www.global.common.status.SuccessStatus

@RestController
@RequestMapping("/blog")
class BlogController(
    private val blogService: BlogService,
) {

    @GetMapping
    fun getBlogList(): Success<List<BlogListResponse>> {
        val result = blogService.getList()
        return Success(result, SuccessStatus.OK)
    }

}