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
import com.meridiane.teacher.databinding.AddLikeDialogBinding
import com.meridiane.teacher.databinding.FragmentStatisticBinding
import com.meridiane.teacher.domain.models.Lesson
import com.meridiane.teacher.presentation.ui.adapters.AdapterStatistics
import com.meridiane.teacher.presentation.ui.adapters.DefaultLoadStateAdapter
import com.meridiane.teacher.presentation.ui.adapters.TryAgainAction
import com.meridiane.teacher.presentation.ui.customView.ProgressContainer
import com.meridiane.teacher.presentation.ui.viewModel.StaticViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StatisticFragment : Fragment() {

    private lateinit var mainLoadStateHolder: DefaultLoadStateAdapter.Holder
    private val viewModel: StaticViewModel by viewModels()
    private var _binding: FragmentStatisticBinding? = null
    private val binding get() = _binding!!

    private val adapterStatistics by lazy {
        AdapterStatistics { lesson ->
            alertDialog(lesson)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerState.state = ProgressContainer.State.Loading
        setupStatisticsList()

        lifecycleScope.launch {
            viewModel.lessonState.collectLatest {
                adapterStatistics.submitData(it)
            }
        }

        viewModel.getQuestions(false)
    }

    private fun setupStatisticsList() {

        val tryAgainAction: TryAgainAction = { adapterStatistics.retry() }

        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapterStatistics.withLoadStateFooter(footerAdapter)

        binding.rcFragmentStatic.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterWithLoadState

        }
        (binding.rcFragmentStatic.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false

        mainLoadStateHolder = DefaultLoadStateAdapter.Holder(
            binding.loadStateView,
            binding.swipeRefreshLayout,
            tryAgainAction
        )

        adapterStatistics.addLoadStateListener { state ->

            binding.containerState.state = when (state.source.refresh) {
                is LoadState.Error -> ProgressContainer.State.Notice("Ошибка")
                is LoadState.Loading -> ProgressContainer.State.Loading
                is LoadState.NotLoading -> {
                    if (adapterStatistics.itemCount == 0) {
                        ProgressContainer.State.Notice("Пустота")

                    } else {
                        binding.swipeRefreshLayout.visibility = View.VISIBLE
                        ProgressContainer.State.Success
                    }
                }

            }
        }
    }

    // Оценка занятия, на данном этапе балл отображается сообщением,
    // в дальнейщем необходимо перезаписать значение
    // like  в классе lesson
    private fun alertDialog(lesson: Lesson) {
        val builder = AlertDialog.Builder(requireContext())
        val rootDialogElement = AddLikeDialogBinding.inflate(activity?.layoutInflater!!)
        var like = 0

        val dialog = builder.setView(rootDialogElement.root).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        rootDialogElement.textLike1.setOnClickListener {
            Toast.makeText(requireContext(),"1 ,балл",Toast.LENGTH_SHORT).show()
            like = 1
            dialog.dismiss()
        }
        rootDialogElement.textLike2.setOnClickListener {
            Toast.makeText(requireContext(),"2 балла",Toast.LENGTH_SHORT).show()
            like = 2
            dialog.dismiss()
        }
        rootDialogElement.textLike3.setOnClickListener {
            Toast.makeText(requireContext(),"3 балла",Toast.LENGTH_SHORT).show()
            like = 3
            dialog.dismiss()
        }
        rootDialogElement.textLike4.setOnClickListener {
            Toast.makeText(requireContext(),"4 балла",Toast.LENGTH_SHORT).show()
            like = 4
            dialog.dismiss()
        }
        rootDialogElement.textLike5.setOnClickListener {
            Toast.makeText(requireContext(),"5 балла",Toast.LENGTH_SHORT).show()
            like = 5
            dialog.dismiss()
        }

        //lesson.like = like

        dialog.show()
    }

}