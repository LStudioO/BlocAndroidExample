package com.lstudio.blocexample.bloc

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*


abstract class Bloc<Action : BlocAction<State>, State>(
    scope: CoroutineScope,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    private val resultChannel = ConflatedBroadcastChannel<State>()

    val currentState: State?
        get() = resultChannel.valueOrNull

    private val actor = scope.actor<Action>(capacity = Channel.UNLIMITED) {
        initState()?.let { initialState -> resultChannel.send(initialState) }
        channel.consumeEach { action ->
            flow {
                action.reduce(currentState).collect { state ->
                    emit(state)
                }
            }
                .flowOn(coroutineDispatcher)
                .collect { state ->
                    resultChannel.send(state)
                }
        }
    }

    fun asFlow() = resultChannel.asFlow()

    protected open fun initState(): State? = null

    protected fun dispatch(action: Action) {
        actor.offer(action)
    }
}
