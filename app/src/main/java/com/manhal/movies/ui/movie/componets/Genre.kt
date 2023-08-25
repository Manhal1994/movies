/* Developed by Manhal */

package com.manhal.movies.ui.movie.componets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.manhal.movies.models.entities.Genre

@Composable
fun Genre(genre: Genre) {
  Surface(
    shape = RoundedCornerShape(32.dp),
    border = BorderStroke(width = 0.8.dp, color = Color.White),
    elevation = 8.dp,
    color = Color.Transparent,
    modifier = Modifier.padding(8.dp)
  ) {

    Text(
      text = genre.name,
      style = MaterialTheme.typography.body1,
      color = Color.White,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 4.dp)
    )
  }
}
