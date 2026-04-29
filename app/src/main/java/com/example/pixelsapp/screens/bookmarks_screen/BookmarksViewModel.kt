package com.example.pixelsapp.screens.bookmarks_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetLikedPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getLikedPhotosUseCase: GetLikedPhotosUseCase
): ViewModel() {

    private val _state = MutableStateFlow(BookmarksState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: BookmarksIntent) {
        when(intent) {
            is BookmarksIntent.LoadData -> loadData()
        }
    }
    private fun loadData() {
        _state.update { it.copy(isLoading = true, isEmpty = false) }
        viewModelScope.launch(Dispatchers.IO) {
            val flow = runCatching {
                getLikedPhotosUseCase()
            }
            flow.onSuccess { data ->
                if (data.isEmpty()) {
                    _state.update { it.copy(isLoading = false, isEmpty = true) }
                } else {
                    _state.update { it.copy(isLoading = false, data = data, isEmpty = false) }
                }
            }.onFailure {
                _state.update { it.copy(isLoading = false, isEmpty = true) }
            }
        }
    }
}