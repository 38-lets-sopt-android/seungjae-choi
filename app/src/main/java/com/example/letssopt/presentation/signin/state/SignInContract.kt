package com.example.letssopt.presentation.signin.state

sealed interface SignInSideEffect {
    data class ShowToast(val message: String) : SignInSideEffect
    data object NavigateToMain : SignInSideEffect
    data object NavigateToSignUp : SignInSideEffect
}