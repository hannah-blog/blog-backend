package site.hannahlog.www.domain.series.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.hannahlog.www.domain.blog.dto.response.BlogListResponse
import site.hannahlog.www.domain.series.dto.request.SeriesRequest
import site.hannahlog.www.domain.series.dto.response.SeriesResponse
import site.hannahlog.www.domain.series.service.SeriesService
import site.hannahlog.www.global.aop.ip.IpCheck
import site.hannahlog.www.global.common.response.ApiResponse.Success
import site.hannahlog.www.global.common.status.SuccessStatus

@RestController
@RequestMapping("/series")
class SeriesController(
    private val seriesService: SeriesService,
) {

    @GetMapping
    fun findAll(): Success<List<SeriesResponse>> {
        val result = seriesService.findAll()
        return Success(result, SuccessStatus.OK)
    }

    @IpCheck
    @PostMapping
    fun save(@RequestBody request: SeriesRequest): Success<SeriesResponse> {
        val result = seriesService.save(request)
        return Success(result, SuccessStatus.CREATED)
    }

    @IpCheck
    @PatchMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody request: SeriesRequest): Success<SeriesResponse> {
        val result = seriesService.update(id, request)
        return Success(result, SuccessStatus.UPDATED)
    }

    @IpCheck
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): Success<Long> {
        seriesService.delete(id)
        return Success(id, SuccessStatus.DELETED)
    }

    @GetMapping("/blogs/{id}")
    fun findBlogsById(@PathVariable id: Long): Success<List<BlogListResponse>> {
        val result = seriesService.findBlogsById(id)
        return Success(result, SuccessStatus.OK)
    }

    @IpCheck
    @PostMapping("/{id}/blogs/{blogId}")
    fun addBlog(@PathVariable id: Long, @PathVariable blogId: Long): Success<Long> {
        seriesService.addBlog(id, blogId)
        return Success(id, SuccessStatus.OK)
    }

    @IpCheck
    @DeleteMapping("/{id}/blogs/{blogId}")
    fun deleteBlog(@PathVariable id: Long, @PathVariable blogId: Long): Success<Long> {
        seriesService.deleteBlog(id, blogId)
        return Success(id, SuccessStatus.OK)
    }

}
