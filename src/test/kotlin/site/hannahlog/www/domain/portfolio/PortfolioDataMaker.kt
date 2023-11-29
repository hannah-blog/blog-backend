package site.hannahlog.www.domain.portfolio

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import site.hannahlog.www.domain.portfolio.dto.request.PortfolioRequest
import site.hannahlog.www.domain.portfolio.entity.Portfolio
import site.hannahlog.www.domain.portfolio.repository.PortfolioRepository
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Component
class PortfolioDataMaker @Autowired constructor(
    internal val portfolioRepository: PortfolioRepository,
) {

    fun save(): Portfolio {
        return portfolioRepository.save(getSaveEntity())
    }

    fun saveAll(count: Long): MutableList<Portfolio> {
        val portfolios: MutableList<Portfolio> = ArrayList();
        for(i in 1..count) portfolios.add(getSaveEntity(i))
        return portfolioRepository.saveAll(portfolios)
    }

    fun getPortfolioOrThrow(id: Long): Portfolio = portfolioRepository.findById(id)
            .orElseThrow { throw LogicException(ErrorStatus.NOT_EXIST_PORTFOLIO) }

    companion object {
        fun getSaveEntity(i: Long? = 1) = Portfolio(
            title = "title$i",
            thumbnailUrl = "https://thumbnailUrl$i.com/$i",
            content = "content$i",
        )

        fun getSaveRequest(i: Long? = 1) = PortfolioRequest(
            title = "title$i",
            thumbnailUrl = "https://thumbnailUrl$i.com/$i",
            content = "content$i",
        )

        fun getUpdateRequest(i: Long? = 1) = PortfolioRequest(
            title = "title$i - update",
            thumbnailUrl = "https://thumbnailUrl$i.com/$i/update",
            content = "content$i - update",
        )
    }

}