package site.hannahlog.www.domain.blog.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType
import org.springframework.data.jpa.repository.JpaRepository
import site.hannahlog.www.domain.blog.entity.Blog
import java.util.*

interface BlogRepository: JpaRepository<Blog, Long> {
    @EntityGraph(attributePaths = ["tags"], type = EntityGraphType.LOAD)
    fun findAllByOrderByCreatedDateDesc(): List<Blog>

    @EntityGraph(attributePaths = ["tags"], type = EntityGraphType.LOAD)
    fun findBlogById(id: Long): Optional<Blog>

    fun findBlogsByIdIsIn(id: List<Long>): List<Blog>

}
