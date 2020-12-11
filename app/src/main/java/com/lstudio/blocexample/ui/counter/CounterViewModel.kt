package com.lstudio.blocexample.ui.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lstudio.blocexample.ui.counter.bloc.CounterBloc
import com.lstudio.blocexample.ui.counter.bloc.CounterState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CounterViewModel(
    private val counterBloc: CounterBloc
) : ViewModel() {

    private val _modelLiveData = MutableLiveData<CounterState>()
    val modelLiveData: LiveData<CounterState> = _modelLiveData

    init {
        collectBlocFlow()
    }

    private fun collectBlocFlow() {
        viewModelScope.launch {
            counterBloc.asFlow()
                .distinctUntilChanged()
                .collect {
                    _modelLiveData.value = it
                }
        }
    }

    fun onCounterIncreaseClicked() {
        counterBloc.increaseCounter()
    }

    fun onCounterDecreaseClicked() {
        counterBloc.decreaseCounter()
    }
}