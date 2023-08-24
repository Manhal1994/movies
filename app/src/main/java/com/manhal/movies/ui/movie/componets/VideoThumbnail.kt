package com.manhal.movies.ui.movie.componets

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import com.manhal.movies.models.Video
import com.manhal.movies.network.Api
import com.manhal.movies.network.compose.NetworkImage
import com.skydoves.landscapist.palette.BitmapPalette
import com.manhal.movies.R

@Composable
fun VideoThumbnail(
    video: Video
) {
    val context = LocalContext.current

    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {

        ConstraintLayout(
            modifier = Modifier
                .width(150.dp)
                .height(100.dp)
                .clickable(
                    onClick = {
                        val playVideoIntent =
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(Api.getYoutubeVideoPath(video.key))
                            )
                        context.startActivity(playVideoIntent)
                    }
                )
        ) {
            val (thumbnail, icon, box, title) = createRefs()

            var palette by remember { mutableStateOf<Palette?>(null) }
            NetworkImage(
                networkUrl = Api.getYoutubeThumbnailPath(video.key),
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(thumbnail) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                bitmapPalette = BitmapPalette {
                    palette = it
                }
            )

            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.icon_youtube),
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .height(20.dp)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Crossfade(
                targetState = palette,
                modifier = Modifier
                    .height(25.dp)
                    .constrainAs(box) {
                        bottom.linkTo(parent.bottom)
                    }
            ) {

                Box(
                    modifier = Modifier
                        .background(Color(it?.darkVibrantSwatch?.rgb ?: 0))
                        .alpha(0.7f)
                        .fillMaxSize()
                )
            }

            Text(
                text = video.name,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.85f)
                    .padding(horizontal = 8.dp)
                    .constrainAs(title) {
                        top.linkTo(box.top)
                        bottom.linkTo(box.bottom)
                    }
            )
        }
    }
}
