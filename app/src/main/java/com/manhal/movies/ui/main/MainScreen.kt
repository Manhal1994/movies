
package com.manhal.movies.ui.main

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import com.manhal.movies.ui.movie.MovieDetailScreen
import com.manhal.movies.ui.movie.MovieScreen
import com.manhal.movies.ui.navigation.NavScreen
import com.manhal.movies.ui.theme.purple200
import com.skydoves.movies.R


@Composable
fun MainScreen() {
  val navController = rememberNavController()


  ProvideWindowInsets {
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
      composable(
        route = NavScreen.Home.route,
        arguments = emptyList()
      ) {
        MovieScreen(
          viewModel = hiltViewModel(),
          selectPoster = {index->
            navController.navigate("${NavScreen.MovieDetails.route}/$index")

          }
        )
      }
      composable(
        route = NavScreen.MovieDetails.routeWithArgument,
        arguments = listOf(
          navArgument(NavScreen.MovieDetails.argument0) { type = NavType.LongType }
        )
      ) { backStackEntry ->

        val posterId =
          backStackEntry.arguments?.getLong(NavScreen.MovieDetails.argument0)
            ?: return@composable

        MovieDetailScreen(posterId, hiltViewModel()) {
          navController.navigateUp()
        }
      }


    }
  }
}

@Preview
@Composable
fun MainAppBar() {
  TopAppBar(
    elevation = 6.dp,
    backgroundColor = purple200,
    modifier = Modifier.height(58.dp)
  ) {
    Text(
      modifier = Modifier
        .padding(8.dp)
        .align(Alignment.CenterVertically),
      text = stringResource(R.string.app_name),
      color = Color.White,
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold
    )
  }
}


