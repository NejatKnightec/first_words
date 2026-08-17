package com.firstwords.feature.home.route

import kotlinx.serialization.Serializable


@Serializable
object HomeRoute

@Serializable
data class SubCategoryRoute(val categoryId: Int)