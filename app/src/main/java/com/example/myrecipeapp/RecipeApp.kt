package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
  val recipeViewModel: MainViewModel = viewModel()
  val viewState by recipeViewModel.categoriesState

  NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
    composable(route = Screen.RecipeScreen.route) {
      RecipeScreen(viewState = viewState, navigateToDetail = {
        // This part if responsible for passing data from the current screen to the detail screen
        // Using key(cat) - value to register de value to recover in next screen
        navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
        navController.navigate(Screen.DetailScreen.route)
      })
    }
    composable(route = Screen.DetailScreen.route) {
      // Recover the value passed
      val category =
        navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat") ?: Category(
          "",
          "",
          "",
          ""
        )

      CategoryDetailScreen(category = category)
    }
  }
}