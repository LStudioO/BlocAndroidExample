package com.lstudio.blocexample.ui.counter.bloc.reducer

import com.lstudio.blocexample.ui.counter.bloc.CounterAction
import com.lstudio.blocexample.ui.counter.bloc.CounterState
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class DecreaseCounterAction(
    private val reducer: DecreaseCounterReducer
) : CounterAction() {
    override suspend fun reduce(state: CounterState?) =
        state?.let { flowOf(reducer.reduce(it)) } ?: emptyFlow()
}

class DecreaseCounterReducer {
    fun reduce(state: CounterState): CounterState {
        return state.copy(value = state.value - 1)
    }
}