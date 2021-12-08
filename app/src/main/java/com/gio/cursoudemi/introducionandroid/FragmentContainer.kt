package com.gio.cursoudemi.introducionandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.gio.cursoudemi.R

class FragmentContainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.containerview,Homefragment())
        }
    }
}