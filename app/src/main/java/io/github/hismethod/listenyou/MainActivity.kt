package io.github.hismethod.listenyou

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem

class MainActivity : AppCompatActivity(), MainFragment.OnFragmentInteractionListener {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val selectedFragment: Fragment
        when (item.itemId) {
            R.id.navigation_home -> selectedFragment = MainFragment.newInstance()
            R.id.navigation_dashboard -> return@OnNavigationItemSelectedListener false
            R.id.navigation_notifications -> return@OnNavigationItemSelectedListener false
            else -> return@OnNavigationItemSelectedListener false
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, selectedFragment)
        transaction.commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, MainFragment.newInstance())
        transaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.i(TAG, "onFragmentInteraction: " + uri)
    }

    companion object {
        private val TAG = "MainActivity";
    }
}
