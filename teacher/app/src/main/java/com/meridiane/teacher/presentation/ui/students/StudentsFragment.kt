package com.meridiane.teacher.presentation.ui.students

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.meridiane.teacher.MainActivity
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.AddStudentCardDialogBinding
import com.meridiane.teacher.databinding.FragmentStudentsBinding
import com.meridiane.teacher.databinding.StudentCardDialogBinding
import com.meridiane.teacher.domain.models.Student
import com.meridiane.teacher.presentation.ui.adapters.AdapterStudents
import com.meridiane.teacher.presentation.ui.adapters.DefaultLoadStateAdapter
import com.meridiane.teacher.presentation.ui.adapters.TryAgainAction
import com.meridiane.teacher.presentation.ui.customView.ProgressContainer
import com.meridiane.teacher.presentation.ui.viewModel.StudentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.ak1.pix.helpers.PixEventCallback
import io.ak1.pix.helpers.addPixToActivity
import io.ak1.pix.models.Mode
import io.ak1.pix.models.Options
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentsFragment : Fragment() {

    private lateinit var mainLoadStateHolder: DefaultLoadStateAdapter.Holder
    private val viewModel: StudentsViewModel by viewModels()
    private var _binding: FragmentStudentsBinding? = null
    private val binding get() = _binding!!

    val options = Options().apply {
        count = 1
        path = "Pix/Camera"
        isFrontFacing = false
        mode = Mode.Picture
    }

    private val adapterStudents by lazy {
        AdapterStudents { student ->
            alertDialog(student)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.containerState.state = ProgressContainer.State.Loading
        setupUsersList()

        binding.addStudent.setOnClickListener {
            alertDialogAddStudent()
        }

        lifecycleScope.launch {
            viewModel.studentState.collectLatest {
                adapterStudents.submitData(it)
            }
        }

        viewModel.getStudents()
    }

    private fun setupUsersList() {

        val tryAgainAction: TryAgainAction = { adapterStudents.retry() }

        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapterStudents.withLoadStateFooter(footerAdapter)

        binding.rcStudents.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterWithLoadState

        }
        (binding.rcStudents.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false

        mainLoadStateHolder = DefaultLoadStateAdapter.Holder(
            binding.loadStateView,
            binding.swipeRefreshLayout,
            tryAgainAction
        )

        adapterStudents.addLoadStateListener { state ->

            binding.containerState.state = when (state.source.refresh) {
                is LoadState.Error -> ProgressContainer.State.Notice("Ошибка")
                is LoadState.Loading -> ProgressContainer.State.Loading
                is LoadState.NotLoading -> {
                    if (adapterStudents.itemCount == 0) {
                        ProgressContainer.State.Notice("Пустота")

                    } else {
                        binding.swipeRefreshLayout.visibility = View.VISIBLE
                        ProgressContainer.State.Success
                    }
                }

            }
        }
    }

    private fun alertDialog(student: Student?) {
        if (student != null) {
            val builder = AlertDialog.Builder(requireContext())
            val rootDialogElement = StudentCardDialogBinding.inflate(activity?.layoutInflater!!)

            val dialog = builder.setView(rootDialogElement.root).create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            with(rootDialogElement) {
                textNameS.text = student.full_name
                textTelegramS.text = student.telegram
                textPhoneS.text = student.telephone_number
                textAddressS.text = student.address
                imagePhoto.load(student.image) {
                    crossfade(true)
                    crossfade(100)
                    placeholder(R.drawable.ic_photo)
                    transformations(CircleCropTransformation())
                }
            }

            rootDialogElement.btTelegramChat.setOnClickListener {
                Toast.makeText(requireContext(), "В разработке", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun alertDialogAddStudent() {
        val builder = AlertDialog.Builder(requireContext())
        val rootDialogElement = AddStudentCardDialogBinding.inflate(activity?.layoutInflater!!)

        val dialog = builder.setView(rootDialogElement.root).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        rootDialogElement.cardPhoto.setOnClickListener {

            val mainActivityView = (activity as MainActivity)


            mainActivityView.addPixToActivity(R.id.container_main, options) {
                when (it.status) {
                    PixEventCallback.Status.SUCCESS -> {

                        }
                    PixEventCallback.Status.BACK_PRESSED -> {
                        childFragmentManager.popBackStack()
                    }
                }

            }
        }

        rootDialogElement.imageAdd.setOnClickListener {
            if (rootDialogElement.textImage.text != null)
                rootDialogElement.imagePhoto.load(rootDialogElement.textImage.text.toString())
            else Toast.makeText(requireContext(),"Добавьте ссылку в поле",Toast.LENGTH_SHORT).show()
        }

        rootDialogElement.btAddStudent.setOnClickListener {
            Toast.makeText(requireContext(), "В разработке", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.show()
    }

}