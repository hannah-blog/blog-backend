package site.hannahlog.www.global.error

import site.hannahlog.www.global.common.status.ErrorStatus

class LogicException(
    val errorStatus: ErrorStatus
) : RuntimeException(errorStatus.getMessage())
