package site.hannahlog.www.global.common.status

import org.springframework.http.HttpStatus

enum class ErrorStatus(
    private val code: HttpStatus,
    private val message: String
): ResponseStatus {
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 문제가 발생했습니다. 나중에 다시 시도해주세요."),

    IP_ERROR(HttpStatus.FORBIDDEN, "허용되지 않은 IP입니다."),

    NOT_EXIST_BLOG(HttpStatus.NOT_FOUND, "존재하지 않는 블로그입니다."),
    NOT_EXIST_TAG(HttpStatus.NOT_FOUND, "존재하지 않는 태그입니다."),
    NOT_EXIST_SERIES(HttpStatus.NOT_FOUND, "존재하지 않는 시리즈입니다."),
    NOT_EXIST_SERIES_BLOGS(HttpStatus.NOT_FOUND, "시리즈에 등록되어있지 않은 블로그입니다."),
    NOT_EXIST_PORTFOLIO(HttpStatus.NOT_FOUND, "존재하지 않는 포트폴리오입니다."),

    NOT_URI(HttpStatus.BAD_GATEWAY, "잘못된 URI 요청입니다."),
    NOT_BODY(HttpStatus.BAD_REQUEST, "잘못된 Body 요청입니다.");

    override fun getStatus(): HttpStatus = code
    override fun getMessage(): String = message
}
