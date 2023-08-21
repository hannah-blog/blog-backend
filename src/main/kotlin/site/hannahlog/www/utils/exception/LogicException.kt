package site.hannahlog.www.utils.exception

class LogicException(
    val errorCode: ErrorCode
) : RuntimeException(errorCode.message)