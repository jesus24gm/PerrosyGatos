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
import com.example.perrosygatos.ViewModel.GatosViewModel
import com.example.perrosygatos.ViewModel.PerrosViewModel
import com.example.perrosygatos.Vistas.VistaGatos
import com.example.perrosygatos.Vistas.VistaPerros
import com.example.perrosygatos.ui.theme.PerrosyGatosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = PerrosViewModel()
            val viewModelG = GatosViewModel()
            PerrosyGatosTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Greeting("Hola", modifier = Modifier.padding(16.dp))

                   //VistaGatos(viewModelG, modifier = Modifier.weight(1f))
                    VistaPerros(viewModel, modifier = Modifier.weight(1f))
                }
                }
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