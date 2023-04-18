package com.meridiane.teacher.presentation.ui.calendar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.FragmentCalendarBinding
import com.meridiane.teacher.presentation.ui.adapters.PagerAdapterCalendar
import com.meridiane.teacher.presentation.ui.viewModel.StaticViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding?= null
    private val binding get() = _binding!!

    private val viewModel: StaticViewModel by viewModels()
    var futureLesson = 0
    var oldLesson = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initial()

        viewModel.getSizeLesson()

        lifecycleScope.launch {
            viewModel.lessonSizeState.collectLatest {
                val list = it
                if (list.isNotEmpty()){
                    futureLesson = list[0]
                    oldLesson = list[1]
                }
            }
        }

        binding.statistic.setOnClickListener {
            showBottomDialog()
        }
    }

    private fun initial() {
        binding.viewPagerCalendar.adapter = PagerAdapterCalendar(this)
        binding.tabsLayout.tabIconTint = null
        TabLayoutMediator(binding.tabsLayout, binding.viewPagerCalendar) { tab, pos ->
            when (pos) {
                0 -> tab.setText(R.string.text_calendar_list)
                1 -> tab.setText(R.string.text_calendar_future_list)
            }
        }.attach()

    }

    private fun showBottomDialog() {
        val modalBottomSheet = BottomDialogFragment.newInstance(futureLesson,oldLesson)
        modalBottomSheet.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        modalBottomSheet.show(childFragmentManager, BottomDialogFragment.TAG)
    }

}