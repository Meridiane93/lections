package com.meridiane.teacher.presentation.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.BottomDialogLayoutBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

open class BottomDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomDialogLayoutBinding
    private var param1: Int? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textPlanFuture.text =
            binding.root.context.getString(R.string.text_future_lesson, param1)

        binding.textPlanOld.text = binding.root.context.getString(R.string.text_old_lesson, param2)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * param1 Parameter 1 old lesson
         * param2 Parameter 2 future lesson
         * @return A new instance of fragment BlankFragment.
         */

        const val TAG = "BottomDialogFragment"

        @JvmStatic
        fun newInstance(param1: Int, param2: Int) =
            BottomDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }
}