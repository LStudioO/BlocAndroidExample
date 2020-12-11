package com.lstudio.blocexample.ui.counter.bloc

import com.lstudio.blocexample.bloc.Bloc
import com.lstudio.blocexample.ui.counter.bloc.reducer.*
import kotlinx.coroutines.CoroutineScope

class CounterBloc(
    scope: CoroutineScope,
    initializeStateReducer: InitializeStateReducer,
    private val increaseCounterReducer: IncreaseCounterReducer,
    private val decreaseCounterReducer: DecreaseCounterReducer
) : Bloc<CounterAction, CounterState>(scope) {

    init {
        dispatch(InitializeStateAction(initializeStateReducer))
    }

    fun increaseCounter() {
        dispatch(IncreaseCounterAction(increaseCounterReducer))
    }

    fun decreaseCounter() {
        dispatch(DecreaseCounterAction(decreaseCounterReducer))
    }
}
