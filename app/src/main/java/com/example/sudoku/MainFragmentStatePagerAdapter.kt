package com.example.sudoku

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class MainFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when(position){
            0 -> return mainhome()
            1 -> return calendar()
            2 -> return Myaccount()

            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount // 자바에서는 { return fragmentCount }

}