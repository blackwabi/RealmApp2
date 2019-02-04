package com.androidkotlincourse.realmapp2.view.see

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidkotlincourse.realmapp2.R
import com.androidkotlincourse.realmapp2.data.Dog
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.list_item.view.*

class DogAdapter(val realm: Realm): RecyclerView.Adapter<DogAdapter.DogHolder>()
{
    private val dogResults: RealmResults<Dog> = realm.where(Dog::class.java).findAll()
    private val listener = { results: RealmResults<Dog> ->
        notifyDataSetChanged()
    }

    fun addDogListener() {
        dogResults.addChangeListener(listener)
    }

    fun removeDogListener() {
        dogResults.removeChangeListener(listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DogHolder(item, item.dog_name, item.dog_age, item.dog_color)
    }

    override fun getItemCount(): Int {
        return dogResults.size
    }

    override fun onBindViewHolder(dogHolder: DogHolder, position: Int) {
        dogHolder.age.text = dogResults[position]?.age?.toString() ?: ""
        dogHolder.color.text = dogResults[position]?.color ?: ""
        dogHolder.name.text = dogResults[position]?.name ?: ""
    }

    class DogHolder(view: View,
                    val name: TextView,
                    val age: TextView,
                    val color: TextView): RecyclerView.ViewHolder(view)
}