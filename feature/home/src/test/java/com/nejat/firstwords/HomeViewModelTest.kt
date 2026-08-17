package com.nejat.firstwords

import com.firstwords.feature.home.HomeViewModel
import com.firstwords.feature.home.dao.Category
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Test
    fun `uiState contains categories from the repository`() = runTest {
        //Given: a fake repository set up to return two specific categories

        val fakeCategoryRepository = FakeCategoryRepository()

        fakeCategoryRepository.categoreisToReturn = listOf(
            Category(categoryId = 1, name = "Animals"),
            Category(categoryId = 2, name = "Food"),
        )

        // When: the viewModel si created

        val viewModel = HomeViewModel(fakeCategoryRepository)
        advanceUntilIdle()

        // Then: uiState should reflect those names on categories

        assertEquals(
            listOf(
                Category(categoryId = 1, name = "Animals"),
                Category(categoryId = 2, name = "Food"),
            ),
            viewModel.uiState.value.categories
        )

    }


}