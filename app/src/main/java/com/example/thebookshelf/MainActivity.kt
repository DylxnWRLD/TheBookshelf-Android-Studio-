package com.example.thebookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thebookshelf.ui.theme.BooksGridScreen
import com.example.thebookshelf.ui.theme.BooksUiState
import com.example.thebookshelf.ui.theme.BooksViewModel
import com.example.thebookshelf.ui.theme.TheBookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheBookshelfTheme {
                val viewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)

                androidx.compose.runtime.LaunchedEffect(Unit) {
                    viewModel.getBooks("messi")
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state = viewModel.booksUiState

                    when (state) {
                        is BooksUiState.Loading -> {
                            Text(
                                text = "Cargando libros...",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        is BooksUiState.Error -> {
                            Text(
                                text = "Error de conexión. Intenta más tarde.",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        is BooksUiState.Success -> {
                            BooksGridScreen(
                                books = state.books,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

