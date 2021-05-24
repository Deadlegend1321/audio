package com.example.audio.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.audio.model.Music

@Database(entities = [Music::class],version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun musicDao(): MusicDao
}