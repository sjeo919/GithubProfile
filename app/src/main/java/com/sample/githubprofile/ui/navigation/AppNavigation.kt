package com.sample.githubprofile.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sample.githubprofile.ui.profile.ProfileScreen
import com.sample.githubprofile.ui.search.SearchScreen

object AppRoutes {
    const val SEARCH = "search"
    const val PROFILE = "profile/{username}"

    fun createProfileRoute(username: String) = "profile/$username"
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.SEARCH,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = AppRoutes.SEARCH) {
            SearchScreen(onUserClick = { username ->
                navController.navigate(AppRoutes.createProfileRoute(username))
            })
        }

        composable(
            route = AppRoutes.PROFILE,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            ProfileScreen(
                username = username ?: "",
                onUpPress = { navController.navigateUp() }
            )
        }
    }
}
