package com.firstwords.feature.home.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val categories = flowOf(
    listOf(
        Category(categoryId = 1, name = "Animals"),
        Category(categoryId = 2, name = "Food"),
        Category(categoryId = 3, name = "Colors"),
        Category(categoryId = 4, name = "Nature"),
        Category(categoryId = 5, name = "Family")
    )
)

private val subCategories = flowOf(
    listOf(
        // Animals (categoryId = 1)
        SubCategory(categoryId = 1, subCategoryId = 1, name = "Dog"),
        SubCategory(categoryId = 1, subCategoryId = 2, name = "Cat"),
        SubCategory(categoryId = 1, subCategoryId = 3, name = "Bird"),
        SubCategory(categoryId = 1, subCategoryId = 4, name = "Fish"),
        SubCategory(categoryId = 1, subCategoryId = 5, name = "Cow"),

        // Food (categoryId = 2)
        SubCategory(categoryId = 2, subCategoryId = 6, name = "Apple"),
        SubCategory(categoryId = 2, subCategoryId = 7, name = "Milk"),
        SubCategory(categoryId = 2, subCategoryId = 8, name = "Bread"),
        SubCategory(categoryId = 2, subCategoryId = 9, name = "Banana"),
        SubCategory(categoryId = 2, subCategoryId = 10, name = "Water"),

        // Colors (categoryId = 3)
        SubCategory(categoryId = 3, subCategoryId = 11, name = "Red"),
        SubCategory(categoryId = 3, subCategoryId = 12, name = "Blue"),
        SubCategory(categoryId = 3, subCategoryId = 13, name = "Yellow"),
        SubCategory(categoryId = 3, subCategoryId = 14, name = "Green"),
        SubCategory(categoryId = 3, subCategoryId = 15, name = "Black"),

        // Nature (categoryId = 4)
        SubCategory(categoryId = 4, subCategoryId = 16, name = "Sun"),
        SubCategory(categoryId = 4, subCategoryId = 17, name = "Moon"),
        SubCategory(categoryId = 4, subCategoryId = 18, name = "Star"),
        SubCategory(categoryId = 4, subCategoryId = 19, name = "Tree"),
        SubCategory(categoryId = 4, subCategoryId = 20, name = "Flower"),

        // Family (categoryId = 5)
        SubCategory(categoryId = 5, subCategoryId = 21, name = "Mama"),
        SubCategory(categoryId = 5, subCategoryId = 22, name = "Dada"),
        SubCategory(categoryId = 5, subCategoryId = 23, name = "Baby"),
        SubCategory(categoryId = 5, subCategoryId = 24, name = "Grandma"),
        SubCategory(categoryId = 5, subCategoryId = 25, name = "Grandpa"),
    )
)

interface CategoryRepository {
    fun getCategoryList(): Flow<List<Category>>
    fun getSubCategoryList(categoryId: Int): Flow<List<SubCategory>>
}

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {
    override fun getCategoryList(): Flow<List<Category>> {
        return categories
    }

    override fun getSubCategoryList(categoryId: Int): Flow<List<SubCategory>> {
        return subCategories.map { subCategories -> subCategories.filter { it.categoryId == categoryId } }
    }
}
