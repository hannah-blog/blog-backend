package site.hannahlog.www.domain.blog.repository

import org.springframework.data.jpa.repository.JpaRepository
import site.hannahlog.www.domain.blog.entity.Blog

interface BlogRepository: JpaRepository<Blog, Long> {
}