/* Developed by Manhal */

package com.manhal.movies.network.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.manhal.movies.ui.theme.shimmerHighLight
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette

@Preview
@Composable
fun NetworkImage(
  @PreviewParameter(NetworkUrlPreviewProvider::class) networkUrl: Any?,
  modifier: Modifier = Modifier,
  circularReveal: CircularReveal? = null,
  contentScale: ContentScale = ContentScale.FillBounds,
  bitmapPalette: BitmapPalette? = null,
  shimmerParams: ShimmerParams? = ShimmerParams(
    baseColor = MaterialTheme.colors.background,
    highlightColor = shimmerHighLight,
    dropOff = 0.65f
  ),
) {
  val url = networkUrl ?: return
  val context = LocalContext.current
  val imageRequest = ImageRequest.Builder(context)
    .diskCachePolicy(CachePolicy.ENABLED)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .data(networkUrl)
    .memoryCacheKey(url.toString())

    .build()
  if (shimmerParams == null) {
    CoilImage(
      imageRequest = imageRequest,
      modifier = modifier,
      contentScale = contentScale,
      circularReveal = circularReveal,
      bitmapPalette = bitmapPalette,
      imageLoader = { context.imageLoader },

      failure = {
        Text(
          text = "image request failed.",
          textAlign = TextAlign.Center,
          style = MaterialTheme.typography.body2,
          modifier = Modifier.fillMaxSize()
        )
      }
    )
  } else {
    CoilImage(
      imageRequest = imageRequest,
      modifier = modifier,
      contentScale = contentScale,
      circularReveal = circularReveal,
      bitmapPalette = bitmapPalette,
      shimmerParams = shimmerParams,
      imageLoader = { context.imageLoader },
      failure = {
        Text(
          text = "image request failed.",
          textAlign = TextAlign.Center,
          style = MaterialTheme.typography.body2,
          modifier = Modifier.fillMaxSize()
        )
      }
    )
  }
}
