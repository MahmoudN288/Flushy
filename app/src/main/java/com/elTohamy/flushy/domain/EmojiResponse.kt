package com.elTohamy.flushy.domain

sealed class EmojiResponse<out T> {
    data object Loading: EmojiResponse<Nothing>()

    data class ReactSuccess<out T>(
        val data: T
    ): EmojiResponse<T>()

    data class ReactFailure(
        val e: Exception?
    ): EmojiResponse<Nothing>()
}