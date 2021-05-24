package com.example.audio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.audio.R
import com.squareup.picasso.Picasso

class MoodItemAdapter(
        private val moodTitles: Array<String>,
        private val moodImages:Array<String>,
        private val listener: OnItemClickListener
) :
        RecyclerView.Adapter<MoodItemAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener{
        val moodImage : ImageView = itemView.findViewById(R.id.moodImage)
        val moodTitle : TextView = itemView.findViewById(R.id.moodTitle)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            val type: String = moodTitles[position]
            if (position!= RecyclerView.NO_POSITION){
                listener.onItemClick(type)
            }

        }// On Clicking any grid item
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mood_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.moodTitle.text = moodTitles[position]
        Picasso.get().load(moodImages[position]).into(holder.moodImage)
    }

    override fun getItemCount(): Int {
        return moodTitles.size
    }

    interface OnItemClickListener{
        fun onItemClick(type: String)
    }

}