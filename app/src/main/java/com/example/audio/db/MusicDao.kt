package com.example.audio.db

import androidx.room.*
import com.example.audio.model.Music

@Dao
interface MusicDao {

    //For inserting data one by one in database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(music: Music)

    //For inserting all data in database
    @Insert
    fun insertAll(list: List<Music>)

    //For deleting a particular column
    @Delete
    fun delete(music: Music)

    //To get all music
    @Query("SELECT * FROM Music")
    fun getAllMusic() : List<Music>

    //To get all music with a particular mood
    @Query("SELECT * FROM Music WHERE mood ==:mood")
    fun getMusicWithMood(mood:String) : List<Music>

    //To get all music with a particular genre
    @Query("SELECT * FROM Music WHERE genre ==:genre")
    fun getMusicWithGenre(genre:String) : List<Music>

    //To get music by name
    @Query("SELECT * FROM Music WHERE name ==:name")
    fun getMusicWithName(name:String) : Music
}