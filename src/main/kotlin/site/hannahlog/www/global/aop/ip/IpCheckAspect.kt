package site.hannahlog.www.global.aop.ip

import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import site.hannahlog.www.global.common.status.ErrorStatus
import site.hannahlog.www.global.error.LogicException

@Aspect
@Component
class IpCheckAspect(
    @Value("\${ip.white.list}")
    private val ipWhiteList: List<String> = listOf(),
) {

    @Before("@annotation(IpCheck)")
    fun checkIp(joinPoint: JoinPoint) {
        val request: HttpServletRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        if (!ipWhiteList.contains(request.remoteAddr)) {
            throw LogicException(ErrorStatus.IP_ERROR)
        }
    }

}
