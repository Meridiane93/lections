package com.meridiane.teacher.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.meridiane.teacher.presentation.ui.calendar.FutureFragment
import com.meridiane.teacher.presentation.ui.calendar.StatisticFragment

class PagerAdapterCalendar(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> StatisticFragment()
            else -> FutureFragment()
        }
    }
}