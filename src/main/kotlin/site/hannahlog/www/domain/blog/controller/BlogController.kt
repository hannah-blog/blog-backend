package site.hannahlog.www.domain.blog.controller

import org.springframework.web.bind.annotation.*
import site.hannahlog.www.domain.blog.dto.request.BlogSaveRequest
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.blog.dto.response.BlogResponse
import site.hannahlog.www.domain.blog.service.BlogService
import site.hannahlog.www.global.aop.ip.IpCheck
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

    @GetMapping("/{id}")
    fun getBlogDetail(@PathVariable id: Long): Success<BlogResponse> {
        val result = blogService.getOne(id)
        return Success(result, SuccessStatus.OK)
    }

    @IpCheck
    @PostMapping
    fun saveBlog(@RequestBody request: BlogSaveRequest): Success<BlogResponse> {
        val result = blogService.saveBlog(request)
        return Success(result, SuccessStatus.CREATED)
    }
}
