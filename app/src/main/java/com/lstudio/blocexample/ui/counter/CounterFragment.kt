package com.lstudio.blocexample.ui.counter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lstudio.blocexample.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CounterFragment : Fragment(R.layout.main_fragment) {

    private lateinit var counterView: TextView
    private lateinit var buttonIncrease: Button
    private lateinit var buttonDecrease: Button

    private val viewModel: CounterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupListeners()
        observe()
    }

    private fun setupView(view: View) {
        counterView = view.findViewById(R.id.message)
        buttonIncrease = view.findViewById(R.id.buttonIncrease)
        buttonDecrease = view.findViewById(R.id.buttonDecrease)
    }

    private fun setupListeners() {
        buttonIncrease.setOnClickListener {
            viewModel.onCounterIncreaseClicked()
        }
        buttonDecrease.setOnClickListener {
            viewModel.onCounterDecreaseClicked()
        }
    }

    private fun observe() {
        viewModel.modelLiveData.observe(viewLifecycleOwner, {
            counterView.text = it.value.toString()
        })
    }

    companion object {
        fun newInstance() = CounterFragment()
    }
}