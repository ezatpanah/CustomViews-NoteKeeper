package com.ezatpanah.notekeeper.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.ezatpanah.notekeeper.adapter.NoteRecyclerAdapter
import com.ezatpanah.notekeeper.R
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.app_bar_items.*
import kotlinx.android.synthetic.main.content_items.*

class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val noteLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    val noteRecyclerAdapter by lazy {
        NoteRecyclerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val activityIntent = Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        displayNotes()
    }

    override fun onResume() {
        super.onResume()
        noteRecyclerAdapter.notifyDataSetChanged()
    }

    fun displayNotes() {
        recyclerItems.layoutManager = noteLayoutManager
        recyclerItems.adapter = noteRecyclerAdapter
        nav_view.menu.findItem(R.id.nav_notes).isChecked = true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_notes -> {
                displayNotes()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(recyclerItems, message, Snackbar.LENGTH_LONG).show()
    }

}
