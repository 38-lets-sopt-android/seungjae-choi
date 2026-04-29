package com.example.letssopt.presentation.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.signup.SignUpRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSignUp(
    navOptions: NavOptions? = null
) {
    navigate(SignUp, navOptions)
}

fun NavGraphBuilder.signUpGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
) {
    composable<SignUp> {
        SignUpRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp,
        )
    }
}


@Serializable
data object SignUp: Route