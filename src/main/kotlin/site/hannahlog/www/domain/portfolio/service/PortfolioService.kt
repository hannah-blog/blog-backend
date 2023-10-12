package site.hannahlog.www.domain.portfolio.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import site.hannahlog.www.domain.portfolio.dto.request.PortfolioRequest
import site.hannahlog.www.domain.portfolio.dto.response.PortfolioListResponse
import site.hannahlog.www.domain.portfolio.dto.response.PortfolioResponse
import site.hannahlog.www.domain.portfolio.dto.response.toListResponse
import site.hannahlog.www.domain.portfolio.dto.response.toResponse
import site.hannahlog.www.domain.portfolio.entity.Portfolio
import site.hannahlog.www.domain.portfolio.repository.PortfolioRepository
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Service
class PortfolioService(
    private val portfolioRepository: PortfolioRepository,
) {

    fun getList(): List<PortfolioListResponse> {
        return portfolioRepository.findAll()
            .map { it.toListResponse() }
    }

    fun getOne(id: Long): PortfolioResponse {
        return portfolioRepository.findById(id)
            .orElseThrow { throw LogicException(ErrorStatus.NOT_EXIST_PORTFOLIO) }
            .toResponse()
    }

    @Transactional
    fun savePortfolio(request: PortfolioRequest): PortfolioResponse {
        val saveEntity = Portfolio.of(request)
        return portfolioRepository.save(saveEntity)
            .toResponse()
    }

    @Transactional
    fun updatePortfolio(id: Long, request: PortfolioRequest): PortfolioResponse {
        val portfolio = portfolioRepository.findById(id)
            .orElseThrow { throw LogicException(ErrorStatus.NOT_EXIST_PORTFOLIO) }
        portfolio.update(request)
        return portfolio.toResponse()
    }

    @Transactional
    fun deletePortfolio(id: Long) {
        portfolioRepository.findById(id)
            .ifPresent { portfolio -> portfolio.delete() }
    }
}
