package com.example.letssopt.presentation.signup.state

sealed interface SignUpSideEffect {
    data class ShowToast(val message: String) : SignUpSideEffect
    data object NavigateToSignIn : SignUpSideEffect
}