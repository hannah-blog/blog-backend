package site.hannahlog.www.domain.tag

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import site.hannahlog.www.domain.tag.dto.request.TagSaveRequest
import site.hannahlog.www.domain.tag.entity.Tag
import site.hannahlog.www.domain.tag.repository.TagRepository
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Component
class TagDataMaker @Autowired constructor(
    internal val tagRepository: TagRepository,
) {

    fun save(): Tag {
        return tagRepository.save(getSaveEntity())
    }

    fun saveAll(count: Long): MutableList<Tag> {
        val tags: MutableList<Tag> = ArrayList();
        for(i in 1..count) tags.add(getSaveEntity(i))
        return tagRepository.saveAll(tags)
    }

    fun getTagOrThrow(tagId: Long): Tag = tagRepository.findById(tagId)
        .orElseThrow { throw LogicException(ErrorStatus.NOT_EXIST_TAG) }

    companion object {
        fun getSaveEntity(i: Long? = 1) = Tag(name = "name$i")

        fun getSaveRequest(i: Long? = 1) = TagSaveRequest(name = "name$i")
    }

}