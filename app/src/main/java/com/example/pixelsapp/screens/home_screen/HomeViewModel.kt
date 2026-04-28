package com.example.pixelsapp.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.usecase.GetFeaturedCollectionsUseCase
import com.example.domain.usecase.GetPhotosUseCase
import com.example.pixelsapp.utils.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val getCollectionsUseCase: GetFeaturedCollectionsUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        viewModelScope.launch {
            networkMonitor.isOnline.collect { online ->
                _state.update { it.copy(isOffline = !online) }
            }
        }
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _state.update { it.copy(isCollectionsLoading = true) }
            getCollectionsUseCase().onSuccess { data ->
                _state.update { it.copy(collections = data, isCollectionsLoading = false) }
                updatePaging()
            }.onFailure { error ->
                _state.update { it.copy(errorMessage = error.message, isCollectionsLoading = false) }
            }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchQuery
                .debounce(700)
                .distinctUntilChanged()
                .collect { query ->
                    updatePaging(query)
                }
        }
        _searchQuery.value = newQuery
        _state.update { it.copy(selectedCollection = newQuery) }
    }

    fun onSearchClicked() {
        updatePaging(_searchQuery.value)
    }

    fun clearSearch() {
        onSearchQueryChange("")
        updatePaging()
    }

    fun onCategorySelected(category: String) {
        if (category == _state.value.selectedCollection) return
        _state.update { it.copy(selectedCollection = category) }
        updatePaging(category)
    }

    private fun updatePaging(query: String = "") {
        val flow = getPhotosUseCase(query).cachedIn(viewModelScope)
        _state.update { it.copy(photosPagingData = flow, selectedCollection = query) }
    }

}