package com.rohitjakhar.dsa450.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.rohitjakhar.dsa450.network.AuthRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    companion object {
        sealed class Events {
            object Loading : Events()
            class Success(val successMessage: String) : Events()
            class Failure(val failureMessage: String) : Events()
        }
    }

    private val authRepo = AuthRepo()

    private var _loginState = Channel<Events>()
    val loginState = _loginState.receiveAsFlow()

    fun signing(credential: AuthCredential) {
        viewModelScope.launch {
            _loginState.send(Events.Loading)
            authRepo.loginUser(
                credential,
                successListener = {
                    viewModelScope.launch {
                        _loginState.send(Events.Success("Login"))
                    }
                },
                failureListener = { exception ->
                    viewModelScope.launch {
                        _loginState.send(Events.Failure(exception.localizedMessage ?: "Failed"))
                    }
                }
            )
        }
    }
}
