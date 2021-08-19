package com.ob.foodapp.feature.result.ui.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ResultPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    private val types = arrayOf("All", "Chicken", "Steak", "Fish", "Other Category")

    override fun getItem(i: Int): Fragment {
        return ResultPageFragment()
    }

    override fun getCount(): Int {
        return types.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return types[position]
    }
}