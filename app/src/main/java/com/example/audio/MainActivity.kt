package com.example.audio

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.audio.adapter.ViewPagerAdapter
import com.example.audio.adapter.mp
import com.example.audio.db.AppDatabase
import com.example.audio.fragment.GridFragment
import com.example.audio.fragment.MoodFragment
import com.example.audio.model.Music
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(
            this, AppDatabase::class.java, "Music.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }// instance of database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs: SharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val first: Boolean = prefs.getBoolean("first",true)

        if(first){
           storingData()
        }



        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.apply {
            add(GridFragment(), "GENRE")
            add(MoodFragment(), "MOOD")
        }
        viewPager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun storingData(){
        db.musicDao().insertAll(listOf<Music>(
            Music("Sunset n Beachz",
                "Calm",
                "Hip-Hop",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Sunset+n+Beachz+-+Ofshane+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Sunset+n+Beachz+-+Ofshane.mp3",
                "02:45",
                "Ofshane",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/hiphop.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/calm.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/sunset.jpg"
            ),
            Music(
                "You Know",
                "Romantic",
                "Hip-Hop",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/mixkit-you-know-308+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/mixkit-you-know-308.mp3",
                "01:41",
                "Arulo",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/hiphop.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/romantic.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/u+know.jpg"
            ),
            Music(
                "Try Me",
                "Sad",
                "Hip-Hop",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/mixkit-try-me-235+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/mixkit-try-me-235.mp3",
                "01:50",
                "Arulo",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/hiphop.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/sad.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/try+me.jpg"
            ),
            Music(
                "Funk",
                "Dramatic",
                "Hip-Hop",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/mixkit-funk-231+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/mixkit-funk-231.mp3",
                "02:11",
                "Arulo",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/hiphop.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dramatic.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/funk.jpg"
            ),
            Music(
                "C.B.P.D",
                "Sad",
                "Hip-Hop",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/mixkit-cbpd-400+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/mixkit-cbpd-400.mp3",
                "01:39",
                "Arulo",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/hiphop.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/sad.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/sad.jpg"
            ),
            Music(
                "Voices",
                "Dark",
                "Ambient",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Voices+-+Patrick+Patrikios+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Voices+-+Patrick+Patrikios.mp3",
                "02:33",
                "Patrick Patrikios",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/ambient.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dark.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/voices.jpg"
            ),
            Music(
                "Feels",
                "Dramatic",
                "Ambient",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Feels+-+Patrick+Patrikios+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Feels+-+Patrick+Patrikios.mp3",
                "02:33",
                "Patrick Patrikios",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/ambient.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dramatic.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/feels.jpg"
            ),
            Music(
                "Beyond",
                "Inspirational",
                "Ambient",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Beyond+-+Patrick+Patrikios+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Beyond+-+Patrick+Patrikios.mp3",
                "03:01",
                "Patrick Patrikios",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/ambient.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/inspire.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/beyond.jpg"
            ),
            Music(
                "Stairway",
                "Happy",
                "Ambient",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Stairway+-+Patrick+Patrikios+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Stairway+-+Patrick+Patrikios.mp3",
                "02:56",
                "Patrick Patrikios",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/ambient.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/happy.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/stairs.jpg"
            ),
            Music(
                "Lights",
                "Calm",
                "Ambient",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Lights+-+Patrick+Patrikios+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Lights+-+Patrick+Patrikios.mp3",
                "02:26",
                "Patrick Patrikios",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/ambient.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/calm.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/lights.jpg"
            ),
            Music(
                "Pouring Out",
                "Dramatic",
                "Jazz and Blues",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Pouring+Out+-+Asher+Fulero+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Pouring+Out+-+Asher+Fulero.mp3",
                "02:13",
                "Asher Fulero",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/jazz.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dramatic.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/pour.jpg"
            ),
            Music(
                "Lukewarm Hazy",
                "Happy",
                "Jazz and Blues",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Lukewarm+Hazy+-+Asher+Fulero+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Lukewarm+Hazy+-+Asher+Fulero.mp3",
                "03:18",
                "Asher Fulero",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/jazz.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/happy.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/hazy.jpg"
            ),
            Music(
                "Lazy Walk",
                "Calm",
                "Jazz and Blues",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Lazy+Walk+-+Cheel+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Lazy+Walk+-+Cheel.mp3",
                "02:41",
                "Cheel",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/jazz.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/calm.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/lazy.jpg"
            ),
            Music(
                "The Joint is Jumpin",
                "Happy",
                "Jazz and Blues",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/The+Joint+is+Jumpin+-+Joel+Cummins+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/The+Joint+is+Jumpin+-+Joel+Cummins.mp3",
                "01:45",
                "Joel Cummins",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/jazz.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/happy.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/joint.jpg"
            ),
            Music(
                "Soft Feeling",
                "Romantic",
                "Jazz and Blues",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Soft+Feeling+-+Cheel+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Soft+Feeling+-+Cheel.mp3",
                "02:55",
                "Cheel",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/jazz.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/romantic.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/soft.jpg"
            ),
            Music(
                "Pastorale",
                "Calm",
                "Classical",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Pastorale+-+Joel+Cummins+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Pastorale+-+Joel+Cummins.mp3",
                "01:34",
                "Joel Cummins",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/classical.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/calm.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/calm.jpg"
            ),
            Music(
                "Sailor's Song",
                "Bright",
                "Classical",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Sailor's+Song+-+Joel+Cummins+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Sailor's+Song+-+Joel+Cummins.mp3",
                "01:40",
                "Joel Cummins",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/classical.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/bright.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/bright.jpg"
            ),
            Music(
                "Sonatina No 2 in F Major Allegro",
                "Happy",
                "Classical",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Sonatina+No+2+in+F+Major+Allegro+-+Joel+Cummins+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Sonatina+No+2+in+F+Major+Allegro+-+Joel+Cummins.mp3",
                "01:47",
                "Joel Cummins",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/classical.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/happy.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/happy.jpg"
            ),
            Music(
                "Hopeful Freedom",
                "Inspirational",
                "Classical",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Hopeful+Freedom+-+Asher+Fulero+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Hopeful+Freedom+-+Asher+Fulero.mp3",
                "02:57",
                "Asher Fulero",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/classical.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/inspire.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/inspire.jpg"
            ),
            Music(
                "Bourree",
                "Dramatic",
                "Classical",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Bourree+-+Joel+Cummins+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Bourree+-+Joel+Cummins.mp3",
                "01:56",
                "Joel Cummins",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/classical.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dramatic.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dramatic.jpg"
            ),
            Music(
                "Mourning Dove",
                "Romantic",
                "Country and Folk",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Mourning+Dove+-+Zachariah+Hickman+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Mourning+Dove+-+Zachariah+Hickman.mp3",
                "01:57",
                "Zachariah Hickman",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/country.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/romantic.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/romantic.jpg"
            ),
            Music(
                "The Colonel",
                "Dark",
                "Country and Folk",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/The+Colonel+-+Zachariah+Hickman+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/The+Colonel+-+Zachariah+Hickman.mp3",
                "02:55",
                "Zachariah Hickman",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/country.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dark.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dark.jpg"
            ),
            Music(
                "Climbing",
                "Dramatic",
                "Country and Folk",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Climbing+-+Reed+Mathis+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Climbing+-+Reed+Mathis.mp3",
                "03:02",
                "Reed Mathis",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/country.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/dramatic.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/country.jpg"
            ),
            Music(
                "In the Sweet By and By",
                "Happy",
                "Country and Folk",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/In+the+Sweet+By+and+By+-+Zachariah+Hickman+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/In+the+Sweet+By+and+By+-+Zachariah+Hickman.mp3",
                "01:32",
                "Zachariah Hickman",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/country.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/happy.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/sweet.jpg"
            ),
            Music(
                "Leaning On the Everlasting Arms",
                "Calm",
                "Country and Folk",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/preview/Leaning+On+the+Everlasting+Arms+-+Zachariah+Hickman+(mp3cut.net).mp3",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/Leaning+On+the+Everlasting+Arms+-+Zachariah+Hickman.mp3",
                "02:59",
                "Zachariah Hickman",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/genre/country.jpg",
                "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/mood/calm.jpg",
                    "https://muditsbucket.s3.ap-south-1.amazonaws.com/songs/album_art/thumbnails/arms.jpg"
            ),

        ))
        val prefs: SharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.apply{
            putBoolean("first",false)
        }.apply()
    }// Function for storing data into the database only 1 time after installing
}