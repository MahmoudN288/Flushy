package com.elTohamy.flushy.components.searchComponents.searchComponents.viewModels

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.elTohamy.flushy.components.searchComponents.searchComponents.data.FavouriteRawData
import com.elTohamy.flushy.components.searchComponents.searchComponents.data.FavouriteData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpandableListViewModel : ViewModel() {

    private val itemsList = MutableStateFlow(listOf<FavouriteData>())
    val items: StateFlow<List<FavouriteData>> get() = itemsList

    private val itemIdsList = MutableStateFlow(listOf<Int>())
    val itemIds: StateFlow<List<Int>> get() = itemIdsList


    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                itemsList.emit(FavouriteRawData.items)
            }
        }
    }

    fun onItemClicked(itemId: Int) {
        itemIdsList.value = itemIdsList.value.toMutableList().also { list ->
            if (list.contains(itemId)) {
                list.remove(itemId)
            } else {
                list.add(itemId)
            }
        }
    }
}