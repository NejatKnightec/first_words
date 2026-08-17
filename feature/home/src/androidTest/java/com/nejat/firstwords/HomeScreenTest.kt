package com.nejat.firstwords

import android.annotation.SuppressLint
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.firstwords.feature.home.HomeScreen
import com.firstwords.feature.home.HomeViewModel
import com.firstwords.feature.home.dao.Category
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun categoriesAreDisplayed_andClickTriggersCallback() {
        val fakeCategoryRepository = FakeCategoryRepository()
        fakeCategoryRepository.categoreisToReturn = listOf(
            Category(categoryId = 1, name = "Animals"),
            Category(categoryId = 2, name = "Food"),
        )

        var clickedCategory: Category? = null

        composeTestRule.setContent {
            HomeScreen(
                onCategoryClick = { clickedCategory = it },
                homeViewModel = HomeViewModel(fakeCategoryRepository)
            )
        }

        composeTestRule.onNodeWithText("Animals").assertIsDisplayed()
        composeTestRule.onNodeWithText("Food").assertIsDisplayed()

        composeTestRule.onNodeWithText("Animals").performClick()

        assert(clickedCategory == Category(categoryId = 1, name = "Animals")) {
            "Expected Animals category click to be captured, got $clickedCategory"
        }



    }


}