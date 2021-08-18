package com.ob.mvicore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

abstract class MviViewModel<ViewState : MviViewState, ViewAction : MviAction>(
    initialState: ViewState
) : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<ViewState>()
    val stateLiveData = stateMutableLiveData.asLiveData()

    private var state by Delegates.observable(initialState) { _, old, new ->
        stateMutableLiveData.value = new
    }

    fun sendAction(viewAction: ViewAction) {
        state = onReduceState(viewAction)
    }

    fun loadData() {
        onLoadData()
    }

    protected open fun onLoadData() {}

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}
