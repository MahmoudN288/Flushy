package com.elTohamy.flushy.data.state

data class SignInState(
    val isSuccess: Boolean = false,
    val isError: String? = null
)