package com.meridiane.teacher.presentation.ui.calendar

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.AddLessonCardDialogBinding
import com.meridiane.teacher.databinding.FragmentFutureBinding
import com.meridiane.teacher.presentation.ui.adapters.AdapterFuture
import com.meridiane.teacher.presentation.ui.adapters.DefaultLoadStateAdapter
import com.meridiane.teacher.presentation.ui.adapters.TryAgainAction
import com.meridiane.teacher.presentation.ui.customView.ProgressContainer
import com.meridiane.teacher.presentation.ui.viewModel.FutureViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class FutureFragment : Fragment() {

    private lateinit var mainLoadStateHolder: DefaultLoadStateAdapter.Holder
    private val viewModel: FutureViewModel by viewModels()
    private var _binding: FragmentFutureBinding? = null
    private val binding get() = _binding!!

    private val adapterFuture by lazy {
        AdapterFuture { lesson ->
            Toast.makeText(requireContext(), "$lesson", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFutureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerState.state = ProgressContainer.State.Loading
        val nowDate = Calendar.getInstance()

        binding.apply {
            calendarView.minDate = nowDate.time.time
            calendarView.maxDate = nowDate.time.time + 8640000000 // range to 100 days
        }

        lifecycleScope.launch {
            viewModel.lessonState.collectLatest {
                adapterFuture.submitData(it)
            }
        }

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            alertDialog(year, month, dayOfMonth)
        }

        setupStatisticsList()

        viewModel.getQuestions(true)
    }

    private fun setupStatisticsList() {

        val tryAgainAction: TryAgainAction = { adapterFuture.retry() }

        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapterFuture.withLoadStateFooter(footerAdapter)

        binding.rcFuture.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterWithLoadState

        }
        (binding.rcFuture.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false

        mainLoadStateHolder = DefaultLoadStateAdapter.Holder(
            binding.loadStateView,
            binding.swipeRefreshLayout,
            tryAgainAction
        )

        adapterFuture.addLoadStateListener { state ->

            binding.containerState.state = when (state.source.refresh) {
                is LoadState.Error -> ProgressContainer.State.Notice("Ошибка")
                is LoadState.Loading -> ProgressContainer.State.Loading
                is LoadState.NotLoading -> {

                    if (adapterFuture.itemCount == 0) {
                        ProgressContainer.State.Notice("Пустота")

                    } else {

                        ProgressContainer.State.Success
                    }
                }

            }
        }
    }

    private fun alertDialog(year: Int, month: Int, dayOfMonth: Int) {

        val builder = AlertDialog.Builder(requireContext())
        val rootDialogElement = AddLessonCardDialogBinding.inflate(activity?.layoutInflater!!)

        val dialog = builder.setView(rootDialogElement.root).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        rootDialogElement.textDate.text =
            binding.root.context.getString(R.string.text_data, dayOfMonth, month, year)

        rootDialogElement.btAddLesson.setOnClickListener {
            Toast.makeText(requireContext(), "В разработке", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

    }

}