package com.android.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.domain.entity.CharacterDetailObject
import com.android.domain.util.ResultOf
import com.android.presentation.viewmodel.CharactersViewModel

@Composable
fun CharacterDetailScreen(viewModel: CharactersViewModel) {
    val characterDetailState by viewModel.characterDetailObject.collectAsState()
    when (characterDetailState) {
        is ResultOf.Success -> {
            val characterDetailObject =
                (characterDetailState as ResultOf.Success<CharacterDetailObject>).value
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = "Name: ${characterDetailObject.name}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Height: ${characterDetailObject.height} cm")
                Text(text = "Mass: ${characterDetailObject.mass} kg")
                Text(text = "Hair Color: ${characterDetailObject.hairColor}")
                Text(text = "Skin Color: ${characterDetailObject.skinColor}")
                Text(text = "Eye Color: ${characterDetailObject.eyeColor}")
                Text(text = "Birth Year: ${characterDetailObject.birthYear}")
                Text(text = "Gender: ${characterDetailObject.gender}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Homeworld: ${characterDetailObject.homeworld}")
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Films:", style = MaterialTheme.typography.titleMedium)
                characterDetailObject.films?.forEach { film ->
                    Text(text = "- $film", style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Vehicles:", style = MaterialTheme.typography.titleMedium)
                if (characterDetailObject.vehicles?.isNotEmpty() == true) {
                    characterDetailObject.vehicles?.forEach { vehicle ->
                        Text(text = "- $vehicle", style = MaterialTheme.typography.bodyMedium)
                    }
                } else {
                    Text(text = "No vehicles", style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Starships:", style = MaterialTheme.typography.titleMedium)
                if (characterDetailObject.starships?.isNotEmpty() == true) {
                    characterDetailObject.starships?.forEach { starship ->
                        Text(text = "- $starship", style = MaterialTheme.typography.bodyMedium)
                    }
                } else {
                    Text(text = "No starships", style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        is ResultOf.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ResultOf.Failure -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Failed to load data:" +
                            " ${(characterDetailState as ResultOf.Failure<CharacterDetailObject>).throwable.message}"
                )
            }
        }
    }
}