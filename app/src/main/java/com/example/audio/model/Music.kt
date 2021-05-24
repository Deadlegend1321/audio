package com.example.audio.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Music(
    val name:String,
    val mood:String,
    val genre:String,
    val preview:String,
    val full:String,
    val duration:String,
    val artist:String,
    val genreImage: String,
    val moodImage: String,
    val thumbnail: String,
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L
)