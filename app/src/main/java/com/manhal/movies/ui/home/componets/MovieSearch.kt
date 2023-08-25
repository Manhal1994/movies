/* Developed by Manhal */

package com.manhal.movies.ui.home.componets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manhal.movies.ui.main.MainViewModel

@Composable
fun MovieSearch(text: String, viewModel: MainViewModel, onChange: (String) -> Unit) {
  return Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp)
      .border(1.dp, Color.Black, shape = RoundedCornerShape(6.dp))

  ) {
    TextField(
      value = text,
      onValueChange = {
        onChange(it)
        viewModel.searchMovies(text)
      },
      placeholder = { Text("Search") },
      leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
      modifier = Modifier
        .fillMaxWidth()
        .onFocusChanged {
        },
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.White,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      ),

    )
  }
}
