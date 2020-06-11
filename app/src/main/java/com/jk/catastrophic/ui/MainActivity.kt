package com.jk.catastrophic.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.jk.catastrophic.R
import com.jk.catastrophic.data.Cat
import com.jk.catastrophic.ui.fragments.CatFragment
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), CatFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onListFragmentInteraction(item: Cat?) {
    }
}
