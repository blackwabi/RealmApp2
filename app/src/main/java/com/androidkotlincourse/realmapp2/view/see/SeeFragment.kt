package com.androidkotlincourse.realmapp2.view.see

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidkotlincourse.realmapp2.R
import com.androidkotlincourse.realmapp2.view.MainActivity
import kotlinx.android.synthetic.main.see_dogs.view.*

class SeeFragment: Fragment() {
    private lateinit var adapter: DogAdapter

    override fun onPause() {
        super.onPause()
        adapter.removeDogListener()
    }

    override fun onResume() {
        super.onResume()
        adapter.addDogListener()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainActivity = activity as MainActivity
        val view = inflater.inflate(R.layout.see_dogs, container, false)

        view.list.layoutManager = LinearLayoutManager(activity)
        adapter = DogAdapter(mainActivity.realm)
        view.list.adapter = adapter

        return view
    }
}