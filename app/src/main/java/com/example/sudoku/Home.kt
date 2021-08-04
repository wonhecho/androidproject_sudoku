package com.example.sudoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_home.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        configureBottomNavigation()
        if(intent.hasExtra("time"))
        {
            var totaltime = intent.getStringExtra("time").toString()
            var FragmentManager = supportFragmentManager
            var FragmentTransaction = FragmentManager.beginTransaction()
            FragmentTransaction.add(R.id.vp_ac_main_frag_pager,calendar.newInstance("${totaltime}","count"))
            FragmentTransaction.commit()
        }



    }

    override fun onBackPressed() {

    }

    private fun configureBottomNavigation() {

        vp_ac_main_frag_pager.adapter = MainFragmentStatePagerAdapter(supportFragmentManager,3)

        tl_ac_main_bottom_menu.setupWithViewPager(vp_ac_main_frag_pager)

        val bottomNaviLayout: View =
            this.layoutInflater.inflate(R.layout.bottom_navigation_tab, null, false)

        tl_ac_main_bottom_menu.getTabAt(0)!!.customView =
            bottomNaviLayout.findViewById(R.id.btn_bottom_navi_home_tab) as RelativeLayout
        tl_ac_main_bottom_menu.getTabAt(1)!!.customView =
            bottomNaviLayout.findViewById(R.id.btn_bottom_navi_search_tab) as RelativeLayout
        tl_ac_main_bottom_menu.getTabAt(2)!!.customView =
            bottomNaviLayout.findViewById(R.id.btn_bottom_navi_add_tab) as RelativeLayout

    }
}
