package site.hannahlog.www.domain.seriesblogs.repository

import org.springframework.data.jpa.repository.JpaRepository
import site.hannahlog.www.domain.blog.entity.Blog
import site.hannahlog.www.domain.series.entity.Series
import site.hannahlog.www.domain.seriesblogs.entity.SeriesBlogs
import java.util.*

interface SeriesBlogsRepository: JpaRepository<SeriesBlogs, Long> {

    fun findBySeriesAndBlog(series: Series, blog: Blog): Optional<SeriesBlogs>

}