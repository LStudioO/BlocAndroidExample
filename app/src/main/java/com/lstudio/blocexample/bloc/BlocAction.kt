package com.lstudio.blocexample.bloc

import kotlinx.coroutines.flow.Flow

interface BlocAction<State> {
    suspend fun reduce(state: State?): Flow<State>
}