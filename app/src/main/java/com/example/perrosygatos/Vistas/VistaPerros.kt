package com.example.perrosygatos.Vistas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.perrosygatos.Modelos.DogBreed
import com.example.perrosygatos.ViewModel.PerrosViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaVistaPerros(navController: NavController, viewModel: PerrosViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Razas de Perros") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("gatos") {
                            popUpTo("perros") { inclusive = false }
                            launchSingleTop = true
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Ver gatos")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(innerPadding)) {
            VistaPerros(viewModel)
        }
    }
}


@Composable
fun VistaPerros(viewModel: PerrosViewModel) {
    val dogBreeds by remember { viewModel::dogBreeds }
    val errorMessage by remember { viewModel::errorMessage }
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var breedImages by remember { mutableStateOf<List<String>>(emptyList()) }

    var selectedBreed by remember { mutableStateOf<DogBreed?>(null) }
    var showBreedsList by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.fetchDogBreeds()
    }

    LaunchedEffect(selectedBreed) {
        selectedBreed?.let { breed ->
            isLoading = true
            breed.imageId?.let { imageId ->
                val imageUrl = viewModel.getDogImage(imageId)
                breedImages = imageUrl?.let { listOf(it) } ?: emptyList()
            }
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        errorMessage?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        if (showBreedsList) {


            LazyColumn(modifier = Modifier.weight(1f)) {
                items(dogBreeds) { breed ->
                    Card(
                        modifier = Modifier

                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                selectedBreed = breed
                                showBreedsList = false
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {

                            Text(
                                text = breed.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        selectedBreed?.let { breed ->
            Text(
                text = "Seleccionaste: ${breed.name}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow {
                items(breedImages) { imageUrl ->
                    Card(
                        modifier = Modifier
                            .size(350.dp)
                            .padding(8.dp)
                            .clickable{
                                val wikipediaUrl = "https://en.wikipedia.org/wiki/${selectedBreed?.name?.replace(" ", "_")}"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wikipediaUrl))
                                context.startActivity(intent)
                                      }   ,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Imagen de ${breed.name}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                                .aspectRatio(1f)
                        )
                    }
                }
            }

            Button(
                onClick = {
                    showBreedsList = true
                    selectedBreed = null
                    breedImages = emptyList()
                },
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = " Volver a la lista", color = Color.White)
            }
        }
    }
}


