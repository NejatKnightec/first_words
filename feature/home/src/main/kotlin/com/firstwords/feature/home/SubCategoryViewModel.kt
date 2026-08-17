package com.firstwords.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.firstwords.feature.home.dao.CategoryRepository
import com.firstwords.feature.home.dao.SubCategory
import com.firstwords.feature.home.route.SubCategoryRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SubCategoryUIState(
    val subCategories: List<SubCategory>,
    val isLoading: Boolean = false,
)

@HiltViewModel
class SubCategoryViewModel @Inject constructor(
    categoryRepository: CategoryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _uiState = MutableStateFlow(SubCategoryUIState(emptyList()))
    var uiState: StateFlow<SubCategoryUIState> = _uiState.asStateFlow()

    val categoryId = savedStateHandle.toRoute<SubCategoryRoute>().categoryId


    init {
        viewModelScope.launch {
            categoryRepository.getSubCategoryList(categoryId).collect { subCategoryList ->
                _uiState.value = SubCategoryUIState(subCategories = subCategoryList)
            }
        }
    }


}