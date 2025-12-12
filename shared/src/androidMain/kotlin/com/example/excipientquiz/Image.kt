package com.example.excipientquiz

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun SharedImage(image: DrawableResource, modifier: Modifier, contentScale: ContentScale) {
    Image(
        painter = painterResource(image),
        contentDescription = null, // Set a meaningful description if needed
        modifier = modifier,
        contentScale = contentScale
    )
}
