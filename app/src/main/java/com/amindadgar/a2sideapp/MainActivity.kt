package com.amindadgar.a2sideapp

import Adapter.viewPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.net.Socket

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)

        viewPager.adapter = viewPagerAdapter(supportFragmentManager)



    }

}
