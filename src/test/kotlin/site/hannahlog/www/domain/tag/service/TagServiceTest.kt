package site.hannahlog.www.domain.tag.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import site.hannahlog.www.domain.tag.TagDataMaker
import site.hannahlog.www.domain.tag.dto.response.TagResponse
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.common.status.ErrorStatus.NOT_EXIST_TAG
import site.hannahlog.www.global.error.LogicException

@SpringBootTest
class TagServiceTest @Autowired constructor(
    private val tagService: TagService,
    private val tagDataMaker: TagDataMaker,
) {

    @Test
    @DisplayName("태그 전체 조회 테스트 - success")
    fun findAll() {
        // given
        val tags = tagDataMaker.saveAll(10)

        // when
        val result = tagService.findAll()

        // then
        assertThat(result.size).isEqualTo(tags.size)
        assertThat(result[0].name).isEqualTo(tags[0].name)
        assertThat(result[4].name).isEqualTo(tags[4].name)
        assertThat(result[9].name).isEqualTo(tags[9].name)
    }

    @Test
    @DisplayName("태그 저장 테스트 - success")
    fun save() {
        // given
        val request = TagDataMaker.getSaveRequest()

        // when
        val result = tagService.save(request)

        // then
        assertThat(result).isInstanceOf(TagResponse::class.java)
        assertThat(result.name).isEqualTo(request.name)
    }

    @Test
    @DisplayName("태그 삭제 테스트 - success")
    fun delete() {
        // given
        val tag = tagDataMaker.save()
        val tagId = tag.id!!

        // when
        tagService.delete(tagId)

        // then
        assertThatThrownBy { getOrThrow(tagId) }
            .isInstanceOf(LogicException::class.java)
            .hasMessage(NOT_EXIST_TAG.getMessage())
    }

    @Test
    @DisplayName("태그 삭제 테스트 - fail : NOT_EXIST_TAG")
    fun deleteFail() {
        // when & then
        assertThatThrownBy { tagService.delete(0) }
            .isInstanceOf(LogicException::class.java)
            .hasMessage(NOT_EXIST_TAG.getMessage())
    }

    private fun getOrThrow(tagId: Long) = tagDataMaker.tagRepository.findById(tagId)
        .orElseThrow { throw LogicException(NOT_EXIST_TAG) }

}