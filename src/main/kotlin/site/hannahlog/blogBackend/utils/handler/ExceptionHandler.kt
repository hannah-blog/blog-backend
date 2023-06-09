package site.hannahlog.blogBackend.utils.handler

import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import site.hannahlog.blogBackend.utils.exception.ErrorCode
import site.hannahlog.blogBackend.utils.exception.LogicException
import site.hannahlog.blogBackend.utils.response.ApiResponse.Error

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(LogicException::class)
    fun handleMemberException(e: LogicException): Error<String> {
        return Error(e.errorCode)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): Error<String> {
        return Error(ErrorCode.NOT_URI)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException): Error<String> {
        return Error(ErrorCode.NOT_BODY)
    }
}