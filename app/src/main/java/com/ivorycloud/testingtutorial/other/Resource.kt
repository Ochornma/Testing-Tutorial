package com.ivorycloud.testingtutorial.other

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> unauthorized(msg: String, data: T?) = Resource(Status.UNAUTHORIZED, null, msg)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    UNAUTHORIZED
}