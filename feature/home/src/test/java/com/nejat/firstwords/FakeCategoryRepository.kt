package com.nejat.firstwords

import com.firstwords.feature.home.dao.Category
import com.firstwords.feature.home.dao.CategoryRepository
import com.firstwords.feature.home.dao.SubCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCategoryRepository : CategoryRepository {

    var categoreisToReturn: List<Category> = listOf()
    var subCategoriesToReturn: List<SubCategory> = emptyList()
    override fun getCategoryList(): Flow<List<Category>> {
        return flowOf(categoreisToReturn)
    }

    override fun getSubCategoryList(categoryId: Int): Flow<List<SubCategory>> {

        return flowOf(subCategoriesToReturn.filter { categoryId == it.categoryId })
    }
}