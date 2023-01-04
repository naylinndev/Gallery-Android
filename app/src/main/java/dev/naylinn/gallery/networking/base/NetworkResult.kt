package dev.naylinn.gallery.networking.base

import retrofit2.Response

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

class HttpError(val throwable: Throwable, val errorCode: Int = 0)

inline fun <T : Any> Response<T>.onSuccess(action: (Response<T>) -> Unit): Response<T> {
    if (isSuccessful) run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

