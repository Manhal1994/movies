
package com.manhal.movies.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import com.manhal.movies.ui.main.MainScreen
import com.manhal.movies.ui.main.MainViewModel
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import com.manhal.movies.ui.theme.MovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {

        MovieComposeTheme {

          MainScreen()
        }
      }
    }
  }
}
