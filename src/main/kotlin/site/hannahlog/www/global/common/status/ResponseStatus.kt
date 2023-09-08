package site.hannahlog.www.global.common.status

import org.springframework.http.HttpStatus

interface ResponseStatus {
    fun getStatus(): HttpStatus
    fun getMessage(): String
}