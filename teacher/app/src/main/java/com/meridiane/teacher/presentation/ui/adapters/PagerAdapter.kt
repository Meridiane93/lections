package com.meridiane.teacher.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.meridiane.teacher.presentation.ui.calendar.CalendarFragment
import com.meridiane.teacher.presentation.ui.profile.ProfileFragment
import com.meridiane.teacher.presentation.ui.questions.QuestionsFragment
import com.meridiane.teacher.presentation.ui.students.StudentsFragment

class PagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ProfileFragment()
            1 -> CalendarFragment()
            2 -> StudentsFragment()
            else -> QuestionsFragment()
        }
    }
}