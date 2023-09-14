package site.hannahlog.www.domain.tag.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.hannahlog.www.domain.tag.dto.request.TagSaveRequest
import site.hannahlog.www.domain.tag.dto.response.TagResponse
import site.hannahlog.www.domain.tag.service.TagService
import site.hannahlog.www.global.aop.ip.IpCheck
import site.hannahlog.www.global.common.response.ApiResponse.Success
import site.hannahlog.www.global.common.status.SuccessStatus

@RestController
@RequestMapping("/tag")
class TagController(
    private val tagService: TagService,
) {

    @GetMapping
    fun findAll(): Success<List<TagResponse>> {
        val result = tagService.findAll()
        return Success(result, SuccessStatus.OK)
    }

    @IpCheck
    @PostMapping
    fun save(@RequestBody request: TagSaveRequest): Success<TagResponse> {
        val result = tagService.save(request)
        return Success(result, SuccessStatus.CREATED)
    }

    @IpCheck
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Success<Long> {
        tagService.delete(id)
        return Success(id, SuccessStatus.DELETED)
    }

}