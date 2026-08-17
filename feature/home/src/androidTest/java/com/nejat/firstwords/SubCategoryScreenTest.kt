package com.nejat.firstwords

import android.annotation.SuppressLint
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.SavedStateHandle
import com.firstwords.feature.home.SubCategoryViewModel
import com.firstwords.feature.home.SubcategoryScreen
import com.firstwords.feature.home.dao.SubCategory
import org.junit.Rule
import org.junit.Test
import kotlin.to

class SubCategoryScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun subCategoriesDisplayed() {
        val fakeCategoryRepository = FakeCategoryRepository()

        fakeCategoryRepository.subCategoriesToReturn = listOf(
            SubCategory(categoryId = 1, subCategoryId = 1, name = "Dog"),
            SubCategory(categoryId = 2, subCategoryId = 1, name = "Egg")
        )

        composeTestRule.setContent {
            SubcategoryScreen(
                onBack = { },
                viewModel = SubCategoryViewModel(
                    fakeCategoryRepository,
                    SavedStateHandle(mapOf("categoryId" to 1))
                )
            )
        }

        composeTestRule.onNodeWithText("Dog").assertIsDisplayed()
        composeTestRule.onNodeWithText("Egg").assertDoesNotExist()

    }
}