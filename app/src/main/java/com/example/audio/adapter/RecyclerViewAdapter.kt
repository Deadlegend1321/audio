package com.example.audio.adapter


import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.audio.model.Music
import com.example.audio.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_row.view.*


lateinit var mp : MediaPlayer
class RecyclerViewAdapter(
        list: List<Music>,
        private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = list
    private var checkedPosition: Int = -1
    var flag: Int = 0


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemClickListener {
        fun onItemClick(name: String)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val songName = view.songName
        val duration = view.duration
        val image = view.image
        val progress = view.progressBar
        val imageButton = view.imageButton
        fun bind(data: Music) {
            songName.text = data.name
            duration.text = data.duration
            Picasso.get().load(data.thumbnail).into(image)
            imageButton.setOnClickListener(View.OnClickListener {
                 onClick()
            })
            if (checkedPosition == -1){
                progress.setVisibility(View.GONE)
                Picasso.get().load(data.thumbnail).into(image)
            }
            else{
                if (checkedPosition == adapterPosition)
                {
                    if (mp.isPlaying) {
                        Picasso.get().load(R.drawable.play).into(image)
                    } else {
                        Picasso.get().load(R.drawable.stop).into(image)
                    }
                }
                else{
                    progress.setVisibility(View.GONE)
                    Picasso.get().load(data.thumbnail).into(image)
                }
            }
        }

        private fun onClick() {
            val position: Int = adapterPosition
            listener.onItemClick(items[position].name)
        }// On Clicking any music download button

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (checkedPosition == adapterPosition) {
                if (p0 != null) {
                    if (p0.isPressed){
                        if (mp.isPlaying) {
                            mp.pause()
                            Picasso.get().load(R.drawable.play).into(image)
                        } else {
                            mp.start()
                            Picasso.get().load(R.drawable.stop).into(image)
                        }
                    }

                }
            }
            else{
                notifyItemChanged(checkedPosition)
                checkedPosition = adapterPosition
                if (flag != 0){
                    mp.stop()
                    mp.release()
                }
                flag = 1
                prepareMediaPlayer(items[adapterPosition].preview)

            }


        }// Function for playing or stopping the music and changing the thumbnail icon accordingly

        private fun prepareMediaPlayer(song: String?){
            try {
                mp = MediaPlayer()
                mp.setDataSource("$song")
                mp.prepareAsync()
                progress.setVisibility(View.VISIBLE)
                mp.setOnPreparedListener {
                    progress.setVisibility(View.GONE)
                    mp.start()
                    Picasso.get().load(R.drawable.stop).into(image)
                }
            }catch (exception: Exception){
            }
        }// Function for preparing the media player to play music

        }


}

