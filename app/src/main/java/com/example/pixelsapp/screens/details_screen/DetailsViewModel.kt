package com.example.pixelsapp.screens.details_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AddToBookmarksUseCase
import com.example.domain.usecase.DownloadPhotoUseCase
import com.example.domain.usecase.GetPhotoDetailUseCase
import com.example.domain.usecase.RemoveFromBookmarksUseCase
import com.example.domain.util.SourceVariants
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
    private val savedState: SavedStateHandle,
    private val downloadPhotoUseCase: DownloadPhotoUseCase,
) : ViewModel() {

    private val photoId = savedState.get<Int>("photo_id") ?: 0
    private val source = savedState.get<String>("source") ?: ""
    private val _state = MutableStateFlow(DetailsState())
    val photoState = _state.asStateFlow()

    fun handleIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.LoadPhoto -> loadData()
            is DetailsIntent.ToggleLike -> toggleLike(photoId, intent.isLiked)
            is DetailsIntent.DownloadPhoto -> downloadPhoto(intent.url, intent.photographer)
        }
    }

    private fun loadData() {
        _state.update { it.copy(loading = true, errorMessage = null) }
        viewModelScope.launch(Dispatchers.IO) {
            val photo = getPhotoDetailUseCase(photoId, SourceVariants.valueOf(source.uppercase()))
            photo.onSuccess { data ->
                _state.update { it.copy(loading = false, photoData = data, isLiked = data.liked) }
            }
                .onFailure { e ->
                    _state.update { it.copy(loading = false, errorMessage = e.message) }
                }
        }
    }

    private fun toggleLike(photoId: Int, isLiked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isLiked) {
                removeFromBookmarksUseCase(photoId)
            } else {
                addToBookmarksUseCase(photoId)
            }
            val photo = getPhotoDetailUseCase(photoId, SourceVariants.LOCAL)
            photo.onSuccess { data ->
                _state.update { it.copy(loading = false, isLiked = data.liked) }
            }
                .onFailure { e ->
                    _state.update { it.copy(loading = false, errorMessage = e.message) }
                }
        }
    }

    private fun downloadPhoto(url: String, photographer: String) {
        downloadPhotoUseCase(url, photographer)
    }

}