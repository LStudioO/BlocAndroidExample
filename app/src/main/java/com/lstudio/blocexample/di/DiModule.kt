package com.lstudio.blocexample.di

import com.lstudio.blocexample.ui.counter.CounterViewModel
import com.lstudio.blocexample.ui.counter.bloc.CounterBloc
import com.lstudio.blocexample.ui.counter.bloc.reducer.DecreaseCounterReducer
import com.lstudio.blocexample.ui.counter.bloc.reducer.IncreaseCounterReducer
import com.lstudio.blocexample.ui.counter.bloc.reducer.InitializeStateReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    factory { CounterBloc(get(), get(), get(), get()) }
    factory { InitializeStateReducer() }
    factory { IncreaseCounterReducer() }
    factory { DecreaseCounterReducer() }
    viewModel { CounterViewModel(get()) }
}