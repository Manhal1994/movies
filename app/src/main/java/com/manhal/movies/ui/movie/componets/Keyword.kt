package com.manhal.movies.ui.movie

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
import com.manhal.movies.models.Keyword
import com.manhal.movies.ui.theme.purple200

@Composable
public fun Keyword(keyword: Keyword) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        elevation = 8.dp,
        color = purple200,
        modifier = Modifier.padding(8.dp)
    ) {

        Text(
            text = keyword.name,
            style = MaterialTheme.typography.body1,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}