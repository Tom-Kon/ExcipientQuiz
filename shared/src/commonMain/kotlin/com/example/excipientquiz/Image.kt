package com.example.excipientquiz

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource

@Composable
expect fun SharedImage(image: DrawableResource, modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.Fit)
