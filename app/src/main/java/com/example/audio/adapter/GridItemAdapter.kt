package com.example.audio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.audio.R
import com.squareup.picasso.Picasso

class GridItemAdapter(
    private val cardTitles: Array<String>,
    private val cardImages: Array<String>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<GridItemAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val cardImage: ImageView = itemView.findViewById(R.id.cardImage)
        val cardTitle: TextView = itemView.findViewById(R.id.cardTitle)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            val type: String = cardTitles[position]
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(type)
            }

        }// On Clicking any grid item

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardTitle.text = cardTitles[position]
        Picasso.get().load(cardImages[position]).into(holder.cardImage)
    }

    override fun getItemCount(): Int {
        return cardTitles.size
    }

    interface OnItemClickListener {
        fun onItemClick(type: String)
    }
}