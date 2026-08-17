package com.firstwords.feature.home.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SubCategory(
    val categoryId: Int, val subCategoryId: Int, val name: String
)