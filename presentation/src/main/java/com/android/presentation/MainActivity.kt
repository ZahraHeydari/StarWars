package com.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.presentation.detail.CharacterDetailScreen
import com.android.presentation.detail.CharacterDetailViewModel
import com.android.presentation.search.CharacterSearchScreen
import com.android.presentation.ui.StarWarsAppTheme
import com.android.presentation.search.CharacterSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val searchViewModel: CharacterSearchViewModel by viewModel()
    private val detailViewModel: CharacterDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarWarsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { AppNavigation(searchViewModel, detailViewModel) }
            }
        }
    }
}

@Composable
fun AppNavigation(
    searchViewModel: CharacterSearchViewModel,
    detailViewModel: CharacterDetailViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            CharacterSearchScreen(navController, searchViewModel)
        }
        composable(
            route = "details/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("characterId")
                ?.let { CharacterDetailScreen(detailViewModel, it) }
        }
    }
}