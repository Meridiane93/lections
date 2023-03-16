package com.meridiane.lection4.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.meridiane.lection4.R
import com.meridiane.lection4.databinding.FragmentBookLectionBinding
import com.meridiane.lection4.presentation.recyclerView.Adapter
import com.meridiane.lection4.presentation.viewModel.MainViewModel

class BookFragment : Fragment(){

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentBookLectionBinding? = null
    private val binding get() = _binding!!

    private val adapter = Adapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookLectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            rcView.layoutManager = LinearLayoutManager(view.context)
            rcView.adapter = adapter

            buttonStart.setOnClickListener {
                adapter.clear()
                viewModel.start()

                textViewError.visibility = View.GONE
                buttonStart.isEnabled = false
            }

            viewModel.liveData.observe(viewLifecycleOwner) {
                adapter.updateAdapter(it)
                buttonStart.text = view.context.getString(R.string.button_start)
                buttonStart.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.purple_700))
                buttonStart.isEnabled = true
            }

            viewModel.liveDataError.observe(viewLifecycleOwner) {

                textViewError.visibility = View.VISIBLE
                textViewError.text = view.context.getString(R.string.error_text, it)
                buttonStart.text = view.context.getString(R.string.button_error)
                buttonStart.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.error_text))
                buttonStart.isEnabled = true
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = BookFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}