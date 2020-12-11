package com.lstudio.blocexample.ui.counter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lstudio.blocexample.R
import com.lstudio.blocexample.databinding.CounterFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CounterFragment : Fragment(R.layout.counter_fragment) {

    private var _binding: CounterFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel: CounterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupListeners()
        observe()
    }

    private fun setupView(view: View) {
        _binding = CounterFragmentBinding.bind(view)
    }

    private fun setupListeners() {
        binding.buttonIncrease.setOnClickListener {
            viewModel.onCounterIncreaseClicked()
        }
        binding.buttonDecrease.setOnClickListener {
            viewModel.onCounterDecreaseClicked()
        }
    }

    private fun observe() {
        viewModel.modelLiveData.observe(viewLifecycleOwner, {
            binding.message .text = it.value.toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CounterFragment()
    }
}