package com.android.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.presentation.screen.CharacterDetailScreen
import com.android.presentation.screen.CharacterSearchScreen
import com.android.presentation.ui.StarWarsAppTheme
import com.android.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarWarsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { AppNavigation(viewModel) }
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: CharactersViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            CharacterSearchScreen(navController, viewModel)
        }
        composable("details/{characterUrl}") {
            CharacterDetailScreen(viewModel)
        }
    }
}