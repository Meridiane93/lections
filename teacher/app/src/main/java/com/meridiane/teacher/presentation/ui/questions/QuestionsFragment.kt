package com.meridiane.teacher.presentation.ui.questions

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
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
import com.meridiane.teacher.databinding.AddQuestionCardDialogBinding
import com.meridiane.teacher.databinding.FragmentQuestionsBinding
import com.meridiane.teacher.presentation.ui.adapters.AdapterQuestions
import com.meridiane.teacher.presentation.ui.adapters.DefaultLoadStateAdapter
import com.meridiane.teacher.presentation.ui.adapters.TryAgainAction
import com.meridiane.teacher.presentation.ui.customView.ProgressContainer
import com.meridiane.teacher.presentation.ui.viewModel.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    private lateinit var mainLoadStateHolder: DefaultLoadStateAdapter.Holder
    private val viewModel: QuestionsViewModel by viewModels()
    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    private val adapterQuestions by lazy {
        AdapterQuestions { question ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(question?.questions!!))
            startActivity(browserIntent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerState.state = ProgressContainer.State.Loading
        setupUsersList()

        binding.addQuestion.setOnClickListener {
            alertDialog()
        }

        lifecycleScope.launch {
            viewModel.studentState.collectLatest {
                adapterQuestions.submitData(it)
            }
        }

        viewModel.getQuestions()
    }

    private fun setupUsersList() {

        val tryAgainAction: TryAgainAction = { adapterQuestions.retry() }

        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapterQuestions.withLoadStateFooter(footerAdapter)

        binding.rcQuestions.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterWithLoadState

        }
        (binding.rcQuestions.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false

        mainLoadStateHolder = DefaultLoadStateAdapter.Holder(
            binding.loadStateView,
            binding.swipeRefreshLayout,
            tryAgainAction
        )

        adapterQuestions.addLoadStateListener { state ->

            binding.containerState.state = when (state.source.refresh) {
                is LoadState.Error -> ProgressContainer.State.Notice("Ошибка")
                is LoadState.Loading -> ProgressContainer.State.Loading
                is LoadState.NotLoading -> {
                    if (adapterQuestions.itemCount == 0) {
                        ProgressContainer.State.Notice("Пустота")

                    } else {
                        binding.swipeRefreshLayout.visibility = View.VISIBLE
                        ProgressContainer.State.Success
                    }
                }

            }
        }
    }

    private fun alertDialog() {

        val builder = AlertDialog.Builder(requireContext())
        val rootDialogElement = AddQuestionCardDialogBinding.inflate(activity?.layoutInflater!!)

        val dialog = builder.setView(rootDialogElement.root).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        rootDialogElement.btAddQuestion.setOnClickListener {
            Toast.makeText(requireContext(), "В разработке", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

    }
}