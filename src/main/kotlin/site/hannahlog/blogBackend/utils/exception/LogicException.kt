package site.hannahlog.blogBackend.utils.exception

class LogicException(
    val errorCode: ErrorCode
) : RuntimeException(errorCode.message)