package com.example.pixelsapp.screens.details_screen.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetPhotoDetailUseCase
import com.example.domain.usecase.AddToBookmarksUseCase
import com.example.domain.usecase.RemoveFromBookmarksUseCase
import com.example.domain.util.SourceVariants
import com.example.pixelsapp.screens.details_screen.DetailsIntent
import com.example.pixelsapp.screens.details_screen.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase,
    private val addToBookmarksUseCase: AddToBookmarksUseCase,
    private val removeFromBookmarksUseCase: RemoveFromBookmarksUseCase,
    private val
    savedState: SavedStateHandle
) : ViewModel() {

    private val photoId = savedState.get<Int>("photo_id") ?: 0
    private val source = savedState.get<String>("source") ?: ""
    private val _state = MutableStateFlow(DetailsState())
    val photoState = _state.asStateFlow()

    fun handleIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.LoadPhoto -> loadData()
            is DetailsIntent.AddToBookmarks -> addToBookmarks(photoId)
            is DetailsIntent.RemoveFromBookmarks -> removeFromBookmarks(photoId)
        }
    }

    private fun loadData() {
        _state.update { it.copy(loading = true, errorMessage = null) }
        viewModelScope.launch(Dispatchers.IO) {
            val photo = getPhotoDetailUseCase(photoId, SourceVariants.valueOf(source.uppercase()))
            photo.onSuccess { data ->
                _state.update { it.copy(loading = false, photoData = data) }
            }
                .onFailure { e ->
                    _state.update { it.copy(loading = false, errorMessage = e.message) }
                }
        }
    }

    private fun addToBookmarks(photoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            addToBookmarksUseCase(photoId)
        }
    }

    private fun removeFromBookmarks(photoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFromBookmarksUseCase(photoId)
        }
    }

}