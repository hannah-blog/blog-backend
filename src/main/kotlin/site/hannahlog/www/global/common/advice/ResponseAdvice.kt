package site.hannahlog.www.global.common.advice

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import site.hannahlog.www.global.common.response.ApiResponse
import site.hannahlog.www.global.common.response.ApiResponse.Error
import site.hannahlog.www.global.common.response.ApiResponse.Success

@RestControllerAdvice
class ResponseAdvice : ResponseBodyAdvice<ApiResponse<*>> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        val isErrorResponse = returnType.parameterType.isAssignableFrom(Error::class.java)
        val isSuccessResponse = returnType.parameterType.isAssignableFrom(Success::class.java)
        return isErrorResponse || isSuccessResponse
    }

    override fun beforeBodyWrite(
        body: ApiResponse<*>?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): ApiResponse<*>? {
        body?.let {
            response.setStatusCode(body.status)
        }
        return body
    }
}