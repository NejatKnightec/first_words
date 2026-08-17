package com.nejat.firstwords

import androidx.lifecycle.SavedStateHandle
import com.firstwords.feature.home.SubCategoryViewModel
import com.firstwords.feature.home.dao.Category
import com.firstwords.feature.home.dao.SubCategory
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class SubCategoryViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val fakeCategoryRepository = FakeCategoryRepository()

    @Test
    fun `uiState reflects categories from repository`() = runTest {
        //Given
        fakeCategoryRepository.subCategoriesToReturn = listOf(
            SubCategory(categoryId = 1, subCategoryId = 1, name = "Dog"),
            SubCategory(categoryId = 2, subCategoryId = 1, name = "milk")
        )


        val viewModel = SubCategoryViewModel(
            categoryRepository = fakeCategoryRepository,
            savedStateHandle = SavedStateHandle(mapOf("categoryId" to 1))
        )

        advanceUntilIdle()

        assertEquals(
            listOf(
                SubCategory(categoryId = 1, subCategoryId = 1, name = "Dog"),
                SubCategory(categoryId = 2, subCategoryId = 1, name = "milk")
            ),
            viewModel.uiState.value.subCategories
        )
    }


    @Test
    fun `uiState reflects subcategories of selected category from repository`() = runTest {
        //Given
        fakeCategoryRepository.categoreisToReturn = listOf(
            Category(name = "Animal", categoryId = 1)
        )

        fakeCategoryRepository.subCategoriesToReturn = listOf(
            SubCategory(categoryId = 1, subCategoryId = 1, name = "Dog"),
            SubCategory(categoryId = 2, subCategoryId = 1, name = "milk")
        )

        val viewModel = SubCategoryViewModel(
            categoryRepository = fakeCategoryRepository,
            savedStateHandle = SavedStateHandle(mapOf("categoryId" to 1))
        )

        advanceUntilIdle()

        assertEquals(
            listOf(SubCategory(categoryId = 1, subCategoryId = 1, name = "Dog")),
            viewModel.uiState.value.subCategories
        )

    }
}