package com.android.presentation.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.presentation.theme.ui.StarWarsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarWarsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            CharacterSearchScreen(navController)
        }
        composable("details/{character}") { backStackEntry ->
            val character = backStackEntry.arguments?.getString("character")
            character?.let {
                CharacterDetailScreen(character = it, navController = navController)
            }
        }
    }
}

@Composable
fun CharacterSearchScreen(navController: NavHostController) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val characters = listOf("Luke Skywalker", "Darth Vader", "Leia Organa") // Mocked data

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Search for a Star Wars Character",
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                ) {
                    if (query.text.isEmpty()) Text("Enter character name", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Results:", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        for (character in characters.filter { it.contains(query.text, ignoreCase = true) }) {
            Text(
                text = character,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navController.navigate("details/$character") }
            )
            Divider()
        }
    }
}

@Composable
fun CharacterDetailScreen(character: String, navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Character Details", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: $character")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Search")
        }
    }
}