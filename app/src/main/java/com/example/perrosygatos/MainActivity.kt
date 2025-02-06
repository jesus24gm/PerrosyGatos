package com.example.perrosygatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perrosygatos.ViewModel.GatosViewModel
import com.example.perrosygatos.ViewModel.PerrosViewModel
import com.example.perrosygatos.Vistas.PantallaVistaGatos
import com.example.perrosygatos.Vistas.PantallaVistaPerros
import com.example.perrosygatos.Vistas.VistaGatos
import com.example.perrosygatos.Vistas.VistaPerros
import com.example.perrosygatos.ui.theme.PerrosyGatosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val perrosViewModel: PerrosViewModel = viewModel()
            val gatosViewModel: GatosViewModel = viewModel()

            NavHost(navController = navController, startDestination = "perros") {
                composable("perros") { PantallaVistaPerros(navController, perrosViewModel) }
                composable("gatos") { PantallaVistaGatos(navController, gatosViewModel) }


            }
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Bienvenido a la aplicación de Perros y Gatos!",
                modifier = modifier

            )
        }

    }
}