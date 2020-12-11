package com.lstudio.blocexample.ui.counter.bloc.reducer

import com.lstudio.blocexample.ui.counter.bloc.CounterAction
import com.lstudio.blocexample.ui.counter.bloc.CounterState
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class InitializeStateAction(
    private val reducer: InitializeStateReducer
) : CounterAction() {
    override suspend fun reduce(state: CounterState?) =
        reducer.reduce()?.let { flowOf(it) } ?: emptyFlow()
}

class InitializeStateReducer {
    fun reduce(): CounterState? {
        return CounterState(0)
    }
}
