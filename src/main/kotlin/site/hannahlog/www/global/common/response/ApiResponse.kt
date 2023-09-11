package site.hannahlog.www.global.common.response

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.http.HttpStatus
import site.hannahlog.www.global.common.status.ResponseStatus

sealed class ApiResponse<T>(
    open val data: T? = null,
    val code: Int? = null,
    val message: String? = null,
    @JsonIgnore
    val status: HttpStatus
) {

    data class Success<T>(override val data: T?, val success: ResponseStatus) :
        ApiResponse<T>(
            data = data,
            code = success.getStatus().value(),
            message = success.getMessage(),
            status = success.getStatus()
        )

    data class Error<T>(val error: ResponseStatus) :
        ApiResponse<T>(
            code = error.getStatus().value(),
            message = error.getMessage(),
            status = error.getStatus()
        )
}
