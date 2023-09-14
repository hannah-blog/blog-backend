package site.hannahlog.www.domain.tag.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import site.hannahlog.www.domain.tag.dto.request.TagSaveRequest
import site.hannahlog.www.domain.tag.dto.response.TagResponse
import site.hannahlog.www.domain.tag.dto.response.toResponse
import site.hannahlog.www.domain.tag.entity.Tag
import site.hannahlog.www.domain.tag.repository.TagRepository
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Service
class TagService(
    private val tagRepository: TagRepository,
) {

    fun findAll(): List<TagResponse> {
        return tagRepository.findAll()
            .map { it.toResponse() }
    }

    @Transactional
    fun save(request: TagSaveRequest): TagResponse {
        val save = Tag.of(request)
        return tagRepository
            .save(save)
            .toResponse()
    }

    @Transactional
    fun delete(id: Long) {
        tagRepository.findById(id)
            .orElseThrow { throw LogicException(ErrorStatus.NOT_EXIST_TAG) }
            .delete()
    }

}