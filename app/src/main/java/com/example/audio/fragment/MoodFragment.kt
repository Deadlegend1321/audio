package com.example.audio.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.audio.R
import com.example.audio.SongsList
import com.example.audio.adapter.MoodItemAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoodFragment : Fragment(), MoodItemAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mood, container, false)
        var recyclerView: RecyclerView? = null
        recyclerView = view.findViewById(R.id.moodItems)

        val moodTitles : Array<String> = resources.getStringArray(R.array.moodTitles)
        val moodImages : Array<String> = resources.getStringArray(R.array.moodImages)

        val adapter = MoodItemAdapter(moodTitles,moodImages,this)
        val gridLayout = GridLayoutManager(context,2)
        recyclerView.layoutManager = gridLayout
        recyclerView.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoodFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(type: String) {
        val i = Intent(context, SongsList::class.java)
        i.putExtra(KEY_1, type)
        startActivity(i)

    }// Function for sending data and opening SongsList activity
}