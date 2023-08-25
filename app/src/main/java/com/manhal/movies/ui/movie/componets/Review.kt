/* Developed by Manhal */

package com.manhal.movies.ui.movie.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.manhal.movies.models.Review

@Composable
fun Review(
  review: Review
) {
  var expanded: Boolean by remember { mutableStateOf(false) }

  Column {

    Spacer(modifier = Modifier.height(12.dp))

    Text(
      text = review.author,
      style = MaterialTheme.typography.body1,
      color = Color.White,
      maxLines = 1,
      fontWeight = FontWeight.Bold,
      overflow = TextOverflow.Ellipsis,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    if (expanded) {
      Text(
        text = review.content,
        style = MaterialTheme.typography.body2,
        color = Color.White,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 15.dp)
          .clickable { expanded = !expanded }
      )
    } else {
      Text(
        text = review.content,
        style = MaterialTheme.typography.body2,
        color = Color.White,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 15.dp)
          .clickable { expanded = !expanded }
      )
    }
  }
}
