package com.androidkotlincourse.realmapp2.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.androidkotlincourse.realmapp2.R
import com.androidkotlincourse.realmapp2.data.Dog
import com.androidkotlincourse.realmapp2.data.Person
import com.androidkotlincourse.realmapp2.view.add.AddFragment
import com.androidkotlincourse.realmapp2.view.see.SeeFragment
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var realm: Realm

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        realm = Realm.getDefaultInstance()

        fab.setOnClickListener {
            val defaultDog = Dog()
            defaultDog.age = 0
            defaultDog.name = "DEFAULT"
            defaultDog.color = "STANDARD"
            realm.beginTransaction()
            realm.insertOrUpdate(defaultDog)
            realm.commitTransaction()
            Snackbar.make(fab, "Added default dog", Snackbar.LENGTH_SHORT).show()
        }

        clearAllData()

        navigateTo(AddFragment())
    }

    private fun clearAllData() {
        realm.beginTransaction()
        realm.where<Dog>().findAll().deleteAllFromRealm()
        realm.where<Person>().findAll().deleteAllFromRealm()
        realm.commitTransaction()
    }

    private fun navigateTo(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_space, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_add_dog -> {
                navigateTo(AddFragment())
            }
            R.id.nav_see_dogs -> {
                navigateTo(SeeFragment())
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
