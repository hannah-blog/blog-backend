package site.hannahlog.www.domain.series.repository

import org.springframework.data.jpa.repository.JpaRepository
import site.hannahlog.www.domain.series.entity.Series

interface SeriesRepository: JpaRepository<Series, Long> {
}