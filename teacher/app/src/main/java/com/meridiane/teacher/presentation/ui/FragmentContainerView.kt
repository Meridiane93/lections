package com.meridiane.teacher.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.FragmentContainerViewPagerBinding
import com.meridiane.teacher.presentation.ui.adapters.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentContainerView : Fragment() {

    private var _binding: FragmentContainerViewPagerBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        _binding = FragmentContainerViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initial()
    }

    private fun initial() {
        binding.viewPagerMyOrders.adapter = PagerAdapter(this)
        binding.tabsLayout.tabIconTint = null
        TabLayoutMediator(binding.tabsLayout, binding.viewPagerMyOrders) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.setText(R.string.view_pager_profile)
                    tab.setIcon(R.drawable.ic_profile)
                }
                1 -> {
                    tab.setIcon(R.drawable.ic_calendar)
                    tab.icon?.alpha = 70
                }
                2 -> {
                    tab.setIcon(R.drawable.ic_student)
                    tab.icon?.alpha = 70
                }
                3 -> {
                    tab.setIcon(R.drawable.ic_questions)
                    tab.icon?.alpha = 70
                }
            }
        }.attach()

        binding.tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.icon?.alpha = 250

                when(tab.position){
                    0  ->  tab.setText(R.string.view_pager_profile)
                    1  ->  tab.setText(R.string.view_pager_calendar)
                    2  ->  tab.setText(R.string.view_pager_students)
                    3  ->  tab.setText(R.string.view_pager_questions)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon?.alpha = 70
                tab.text = null
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

}