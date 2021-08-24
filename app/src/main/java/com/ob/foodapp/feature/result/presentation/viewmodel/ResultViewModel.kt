package com.ob.foodapp.feature.result.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ob.foodapp.feature.result.domain.usecase.GetItemsUseCase
import com.ob.foodapp.feature.result.presentation.action.ResultAction
import com.ob.foodapp.feature.result.presentation.mapper.UiResultMapper
import com.ob.foodapp.feature.result.presentation.model.UiResultItem
import com.ob.foodapp.feature.result.presentation.viewstate.ResultViewState
import com.ob.mvicore.MviViewModel
import kotlinx.coroutines.launch

class ResultViewModel(
    private val getItemsUseCase: GetItemsUseCase,
    private val mapper: UiResultMapper
) : MviViewModel<ResultViewState, ResultAction>(
    ResultViewState.InitialState
) {
    private var likeList: ArrayList<String> = arrayListOf()
    private var userId: String? = null

    override fun onReduceState(viewAction: ResultAction) = when (viewAction) {
        is ResultAction.GetItemsSuccess -> ResultViewState.GetItems(
            list = with(mapper) { viewAction.list.map { it.fromDomainToUi(likeList) } } as MutableList<UiResultItem>
        )
        ResultAction.GetItemsError -> ResultViewState.NotFoundItems
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun setLikes(likes: MutableList<String>) {
        this.likeList = likes as ArrayList<String>
    }

    fun getItemsByCategory(category: String) {
        viewModelScope.launch {
            try {
                getItemsUseCase.execute(category) { itemList ->
                    sendAction(ResultAction.GetItemsSuccess(itemList))
                }
            } catch (e: Exception) {
                sendAction(ResultAction.GetItemsError)
            }
        }
    }
}