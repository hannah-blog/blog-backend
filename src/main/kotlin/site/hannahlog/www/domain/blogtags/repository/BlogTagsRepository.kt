package site.hannahlog.www.domain.blogtags.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.blogtags.entity.BlogTags
import site.hannahlog.www.domain.tag.entity.Tag

interface BlogTagsRepository: JpaRepository<BlogTags, Long> {
}
