package com.example.excipientquiz

fun formatTime(time: Long): String {
    val seconds = time % 60
    val minutes = (time / 60) % 60
    return "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
}
