package site.hannahlog.www.domain.portfolio.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import site.hannahlog.www.domain.portfolio.PortfolioDataMaker
import site.hannahlog.www.global.common.status.ErrorStatus.NOT_EXIST_PORTFOLIO
import site.hannahlog.www.global.error.LogicException
import site.hannahlog.www.helper.ServiceTest

class PortfolioServiceTest @Autowired constructor(
    private val portfolioService: PortfolioService,
    private val portfolioDataMaker: PortfolioDataMaker,
): ServiceTest() {

    @Test
    @DisplayName("포트폴리오 전체 조회 테스트 - success")
    fun getList() {
        // given
        val portfolios = portfolioDataMaker.saveAll(10)

        // when
        val result = portfolioService.getList()

        // then
        assertThat(result[0].title).isEqualTo(portfolios[0].title)
        assertThat(result[4].title).isEqualTo(portfolios[4].title)
        assertThat(result[9].title).isEqualTo(portfolios[9].title)
    }

    @Test
    @DisplayName("포트폴리오 단건 조회 테스트 - success")
    fun getOne() {
        // given
        val portfolio = portfolioDataMaker.save()

        // when
        val result = portfolioService.getOne(portfolio.id!!)

        // then
        assertThat(result).isNotNull
        assertThat(result.title).isEqualTo(portfolio.title)
        assertThat(result.thumbnailUrl).isEqualTo(portfolio.thumbnailUrl)
        assertThat(result.content).isEqualTo(portfolio.content)
    }

    @Test
    @DisplayName("포트폴리오 단건 조회 테스트 - fail : NOT_EXIST_PORTFOLIO")
    fun getOneFail() {
        // when & then
        assertThatThrownBy { portfolioService.getOne(0L) }
            .isInstanceOf(LogicException::class.java)
            .hasMessage(NOT_EXIST_PORTFOLIO.getMessage())
    }

    @Test
    @DisplayName("포트폴리오 저장 테스트 - success")
    fun savePortfolio() {
        // given
        val request = PortfolioDataMaker.getSaveRequest()

        // when
        val result = portfolioService.savePortfolio(request)

        // then
        assertThat(result).isNotNull
        assertThat(result.title).isEqualTo(request.title)
        assertThat(result.thumbnailUrl).isEqualTo(request.thumbnailUrl)
        assertThat(result.content).isEqualTo(request.content)
    }

    @Test
    @DisplayName("포트폴리오 수정 테스트 - success")
    fun updatePortfolio() {
        // given
        val portfolio = portfolioDataMaker.save()
        val request = PortfolioDataMaker.getUpdateRequest()

        // when
        val result = portfolioService.updatePortfolio(portfolio.id!!, request)

        // then
        assertThat(result).isNotNull
        assertThat(result.title).isEqualTo(request.title)
        assertThat(result.thumbnailUrl).isEqualTo(request.thumbnailUrl)
        assertThat(result.content).isEqualTo(request.content)
    }

    @Test
    @DisplayName("포트폴리오 수정 테스트 - fail : NOT_EXIST_PORTFOLIO")
    fun updatePortfolioFail() {
        // given
        val request = PortfolioDataMaker.getUpdateRequest()

        // when & then
        assertThatThrownBy { portfolioService.updatePortfolio(0L, request) }
            .isInstanceOf(LogicException::class.java)
            .hasMessage(NOT_EXIST_PORTFOLIO.getMessage())
    }

    @Test
    @DisplayName("포트폴리오 삭제 테스트 - success")
    fun deletePortfolio() {
        // given
        val portfolio = portfolioDataMaker.save()
        val portfolioId = portfolio.id!!

        // when
        portfolioService.deletePortfolio(portfolioId)

        // then
        assertThatThrownBy { portfolioDataMaker.getPortfolioOrThrow(portfolioId) }
            .isInstanceOf(LogicException::class.java)
            .hasMessage(NOT_EXIST_PORTFOLIO.getMessage())
    }

}
