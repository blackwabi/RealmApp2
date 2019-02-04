package com.androidkotlincourse.realmapp2.view.add

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidkotlincourse.realmapp2.R
import com.androidkotlincourse.realmapp2.data.Dog
import com.androidkotlincourse.realmapp2.view.MainActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*

class AddFragment: Fragment() {
    lateinit var realm: Realm
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainActivity = activity as MainActivity
        realm = mainActivity.realm
        val view = inflater.inflate(R.layout.content_main, container, false)
        view.button_add_dog.setOnClickListener { view ->
            val doggie = Dog()
            doggie.name = edit_dog_name.text.toString()
            doggie.age = edit_dog_age.text.toString().toInt()
            doggie.color = edit_dog_color.text.toString()
            insertOrUpdateDog(doggie)
            edit_dog_age.text.clear()
            edit_dog_name.text.clear()
            edit_dog_color.text.clear()
            Snackbar.make(view, "Added/Updated Dog!", Snackbar.LENGTH_SHORT).show()
        }

        return view
    }

    private fun insertOrUpdateDog(dog: Dog) {
        realm.beginTransaction()
        realm.insertOrUpdate(dog)
        realm.commitTransaction()
    }
}