package site.hannahlog.www.domain.blog.entity

import jakarta.persistence.*
import site.hannahlog.www.domain.model.BaseEntity
import site.hannahlog.www.domain.tag.entity.Tag

@Entity
class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,
    private val title: String,
    private val thumbnailUrl: String,

    @Column(columnDefinition = "TEXT")
    private val content: String,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    private val tags: List<Tag>,
): BaseEntity() {
}