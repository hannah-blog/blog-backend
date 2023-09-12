package site.hannahlog.www.global.error

import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import site.hannahlog.www.global.common.response.ApiResponse.Error
import site.hannahlog.www.global.common.status.ErrorStatus

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): Error<String> {
        return Error(ErrorStatus.SERVER_ERROR)
    }

    @ExceptionHandler(LogicException::class)
    fun handleMemberException(e: LogicException): Error<String> {
        return Error(e.errorStatus)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): Error<String> {
        return Error(ErrorStatus.NOT_URI)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException): Error<String> {
        return Error(ErrorStatus.NOT_BODY)
    }
}