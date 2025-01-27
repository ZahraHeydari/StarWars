package com.android.presentation.search

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.domain.util.ResultOf

@Composable
fun CharacterSearchScreen(
    navController: NavHostController,
    viewModel: CharacterSearchViewModel
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val characterResponseState = viewModel.characterResponseObject.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Search for a Star Wars Character",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = query,
            singleLine = true,
            onValueChange = {
                query = it
                viewModel.searchCharacters(it.text) // Trigger search
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (query.text.isEmpty()) Text("Enter character name")
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Results:", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))

        when (val result = characterResponseState.value) {
            is ResultOf.Success -> {
                val characters = result.value.results
                if (characters.isEmpty()) {
                    Text(text = "No results found", modifier = Modifier.padding(8.dp))
                } else {
                    LazyColumn {
                        items(characters) { character ->
                            Text(
                                text = character.name ?: "Unknown",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        val characterId = Uri.parse(character.url).lastPathSegment
                                        navController.navigate(route = "details/$characterId")
                                    }
                            )
                            Divider()
                        }
                    }
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
                        text = "Failed to load data: ${result.throwable.message}"
                    )
                }
            }

            is ResultOf.Nothing -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = """
                        You have not searched
                         any characters yet!
                    """.trimIndent()
                    )
                }
            }
        }
    }
}