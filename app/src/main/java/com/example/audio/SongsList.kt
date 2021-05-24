package com.example.audio

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.audio.adapter.RecyclerViewAdapter
import com.example.audio.adapter.mp
import com.example.audio.db.AppDatabase
import com.example.audio.fragment.KEY_1
import com.example.audio.fragment.KEY_2
import kotlinx.android.synthetic.main.activity_songs_list.*
import kotlinx.android.synthetic.main.recyclerview_row.view.*
import java.util.jar.Manifest


class SongsList : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    val db by lazy {
        Room.databaseBuilder(
                this, AppDatabase::class.java, "Music.db"
        ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }// Instance of database

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    val STORAGE_PERMISSION_CODE: Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs_list)

        val gm = intent.getStringExtra(KEY_1)
        type.text = gm
        val grid = intent.getStringExtra(KEY_2)

        listItems.apply {
            layoutManager = LinearLayoutManager(this@SongsList)
            if (grid == "true") {
                val list = db.musicDao().getMusicWithGenre(gm.toString())
                if (list.isNotEmpty()) {
                    recyclerViewAdapter = RecyclerViewAdapter(list, this@SongsList)
                    adapter = recyclerViewAdapter
                    val divider = DividerItemDecoration(applicationContext, 1)
                    addItemDecoration(divider)
                }
            } else {
                val list = db.musicDao().getMusicWithMood(gm.toString())
                if (list.isNotEmpty()) {
                    recyclerViewAdapter = RecyclerViewAdapter(list, this@SongsList)
                    adapter = recyclerViewAdapter
                    val divider = DividerItemDecoration(applicationContext, 1)
                    addItemDecoration(divider)
                }
            }

        }// adding music according to genre or mood

    }

    fun BackButton(view: View) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }// Function for going back

    override fun onItemClick(name: String) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){

                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
            }
            else{
                startDownloading(name)
            }
        }
        else{
            startDownloading(name)
        }

    }// Function initiated at the click of download button

    private fun startDownloading(name : String) {
        val song = db.musicDao().getMusicWithName(name).full
        val request = DownloadManager.Request(Uri.parse(song))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle(name)
        request.setDescription("Music downloading")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${System.currentTimeMillis()}")

        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }// Function for downloading a particular file

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //startDownloading()
                }
                else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }// Function for checking Permissions



    override fun onStop() {
        super.onStop()
        mp.release()
    }// Function used when leaving the current activity to stop the media player


    }

