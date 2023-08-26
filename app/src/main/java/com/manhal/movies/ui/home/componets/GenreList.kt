/* Developed by Manhal */

package com.manhal.movies.ui.home.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.manhal.movies.models.entities.Genre
import com.manhal.movies.ui.main.MainViewModel

@Composable
fun GenreList(
  genres: State<List<Genre>>,
  selectedGenre: State<List<Int>>,
  viewModel: MainViewModel,
  text: String
) {
  LazyRow(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
    items(count = genres.value.size) { index ->
      Box(
        modifier = Modifier
          .height(30.dp)
          .clip(RoundedCornerShape(12.dp))
          .border(
            width = 0.9.dp,
            shape = RoundedCornerShape(12.dp),
            color = Color.Black,
          )
          .then(
            if (selectedGenre.value.contains(genres.value[index].id)) Modifier.background(
              color = MaterialTheme.colors.primary
            ) else Modifier.background(color = Color.White)
          )
          .padding(horizontal = 8.dp)
          .clickable {
            viewModel.toggleGenreFilter(genres.value[index].id)
            viewModel.searchMovies()
          },
        contentAlignment = Alignment.Center
      ) {

        Text(
          modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
          text = genres.value[index].name,
          color = (if (selectedGenre.value.contains(genres.value[index].id)) Color.White else Color.Black),
          textAlign = TextAlign.Center,
        )
      }
      Spacer(modifier = Modifier.width(8.dp))
    }
  }
}
