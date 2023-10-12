package site.hannahlog.www.domain.portfolio.controller

import org.springframework.web.bind.annotation.*
import site.hannahlog.www.domain.portfolio.dto.request.PortfolioRequest
import site.hannahlog.www.domain.portfolio.dto.response.PortfolioListResponse
import site.hannahlog.www.domain.portfolio.dto.response.PortfolioResponse
import site.hannahlog.www.domain.portfolio.service.PortfolioService
import site.hannahlog.www.global.common.response.ApiResponse.Success
import site.hannahlog.www.global.common.status.SuccessStatus

@RestController
@RequestMapping("/portfolio")
class PortfolioController(
    private val portfolioService: PortfolioService,
) {

    @GetMapping
    fun getPortfolioList(): Success<List<PortfolioListResponse>> {
        val result = portfolioService.getList()
        return Success(result, SuccessStatus.OK)
    }

    @GetMapping("/{id}")
    fun getPortfolioDetail(@PathVariable id: Long): Success<PortfolioResponse> {
        val result = portfolioService.getOne(id)
        return Success(result, SuccessStatus.OK)
    }

    @PostMapping
    fun savePortfolio(@RequestBody request: PortfolioRequest): Success<PortfolioResponse> {
        val result = portfolioService.savePortfolio(request)
        return Success(result, SuccessStatus.CREATED)
    }

    @PatchMapping("/{id}")
    fun updatePortfolio(@PathVariable id: Long, @RequestBody request: PortfolioRequest): Success<PortfolioResponse> {
        val result = portfolioService.updatePortfolio(id, request)
        return Success(result, SuccessStatus.UPDATED)
    }

    @DeleteMapping("/{id}")
    fun deletePortfolio(@PathVariable id: Long): Success<String> {
        portfolioService.deletePortfolio(id)
        return Success(null, SuccessStatus.DELETED)
    }

}