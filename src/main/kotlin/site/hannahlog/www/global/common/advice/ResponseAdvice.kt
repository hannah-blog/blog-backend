package site.hannahlog.www.global.common.advice

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import site.hannahlog.www.global.common.response.ApiResponse


@ControllerAdvice
class ResponseAdvice : ResponseBodyAdvice<ApiResponse<*>> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.parameterType.isAssignableFrom(ApiResponse::class.java)
    }

    override fun beforeBodyWrite(
        @Nullable body: ApiResponse<*>?,
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