package com.nejat.firstwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firstwords.core.designsystem.FirstWordsAppTheme
import com.firstwords.feature.home.HomeScreen
import com.firstwords.feature.home.SubcategoryScreen
import com.firstwords.feature.home.route.HomeRoute
import com.firstwords.feature.home.route.SubCategoryRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstWordsAppTheme(
                content = { FirstWordsApp() }
            )
        }
    }
}

@Composable
fun FirstWordsApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onCategoryClick = { category ->
                    navController.navigate(SubCategoryRoute(category.categoryId))
                }

            )
        }
        composable<SubCategoryRoute> {
            SubcategoryScreen(
                onBack = { navController.navigate(HomeRoute) }
            )
        }

    }
}
