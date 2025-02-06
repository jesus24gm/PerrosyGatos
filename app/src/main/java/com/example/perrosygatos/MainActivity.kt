package com.example.perrosygatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perrosygatos.ViewModel.GatosViewModel
import com.example.perrosygatos.ViewModel.PerrosViewModel
import com.example.perrosygatos.Vistas.PantallaVistaGatos
import com.example.perrosygatos.Vistas.PantallaVistaPerros

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



}