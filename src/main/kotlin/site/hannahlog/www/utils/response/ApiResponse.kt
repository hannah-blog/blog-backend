package site.hannahlog.www.utils.response

import site.hannahlog.www.utils.exception.ErrorCode

sealed class ApiResponse<T>(
    open val data: T? = null,
    val code: String? = null,
    val message: String? = null
) {

    data class Success<T>(override val data: T?) : ApiResponse<T>(data = data, "200", message = "OK")

    data class Error<T>(val error: ErrorCode) : ApiResponse<T>(code = error.code, message = error.message)
}