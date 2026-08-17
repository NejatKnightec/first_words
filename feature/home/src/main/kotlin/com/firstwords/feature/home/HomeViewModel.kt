package com.firstwords.feature.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firstwords.feature.home.dao.Category
import com.firstwords.feature.home.dao.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class HomeUIState(
    val categories: List<Category> = listOf<Category>(),
    val isLoading: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(repository: CategoryRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    var uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getCategoryList().collect { categories ->
                _uiState.value = HomeUIState(categories = categories)
            }
        }
    }
}