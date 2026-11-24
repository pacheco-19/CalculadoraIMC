package com.pacheco.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.pacheco.calculadoraimc.model.Persona
import com.pacheco.calculadoraimc.screens.FormularioScreen
import com.pacheco.calculadoraimc.screens.ResultadoScreen
import com.pacheco.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                AppImc()
            }
        }
    }
}

@Composable
fun AppImc() {
    // Si persona es null -> formulario. Si tiene datos -> resultado
    var persona by remember { mutableStateOf<Persona?>(null) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val personaActual = persona

        if (personaActual == null) {
            FormularioScreen(
                modifier = Modifier.padding(innerPadding),
                onCalcular = { persona = it }
            )
        } else {
            ResultadoScreen(
                persona = personaActual,
                modifier = Modifier.padding(innerPadding),
                onRegresar = { persona = null }
            )
        }
    }
}
