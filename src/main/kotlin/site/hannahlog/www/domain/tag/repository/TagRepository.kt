package site.hannahlog.www.domain.tag.repository

import org.springframework.data.jpa.repository.JpaRepository
import site.hannahlog.www.domain.tag.entity.Tag

interface TagRepository: JpaRepository<Tag, Long> {

    fun findTagsByIdIsIn(ids: List<Long>): List<Tag>

}