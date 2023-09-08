package site.hannahlog.www.global.common.status

import org.springframework.http.HttpStatus

enum class SuccessStatus(
    private val code: HttpStatus,
    private val message: String
): ResponseStatus {
    OK(HttpStatus.OK, "OK"),
    CREATED(HttpStatus.CREATED, "생성되었습니다."),
    UPDATED(HttpStatus.CREATED, "수정되었습니다."),
    DELETED(HttpStatus.NO_CONTENT,  "삭제되었습니다.")
    ;

    override fun getStatus(): HttpStatus = code
    override fun getMessage(): String = message
}
