package site.hannahlog.www.domain.portfolio.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import site.hannahlog.www.domain.portfolio.entity.Portfolio

interface PortfolioRepository: JpaRepository<Portfolio, Long> {
}
