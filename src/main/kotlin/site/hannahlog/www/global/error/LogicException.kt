package site.hannahlog.www.global.error

class LogicException(
    val errorCode: ErrorCode
) : RuntimeException(errorCode.message)
