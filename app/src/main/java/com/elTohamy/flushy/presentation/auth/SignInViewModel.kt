package com.elTohamy.flushy.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elTohamy.flushy.data.repos.firebase.SignInResult
import com.elTohamy.flushy.data.state.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {

    private var _signInState = MutableStateFlow<SignInState>(value = SignInState())
    val signInState: StateFlow<SignInState> = _signInState.asStateFlow()
    fun signInUser(result: SignInResult) = viewModelScope.launch {
        _signInState.update {
            it.copy(
                isSuccess = result.data != null,
                isError = null
            )
        }
    }

    fun resetState() {
        _signInState.update { SignInState() }
    }
}