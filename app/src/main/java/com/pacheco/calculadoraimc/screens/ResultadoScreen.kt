package com.pacheco.calculadoraimc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacheco.calculadoraimc.model.*

@Composable
fun ResultadoScreen(
    persona: Persona,
    modifier: Modifier = Modifier,
    onRegresar: () -> Unit
) {
    val imc = calcularImc(persona.peso, persona.estatura)
    val valor = valorCualitativo(imc)
    val imagen = obtenerImagen(persona.sexo, imc)

    // Color verde para el resultado
    val colorValor = Color(0xFF2E7D32)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // NOMBRE
        Text(
            text = persona.nombre,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IMC
        Text(
            text = "Tu IMC es", 
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "%.1f".format(imc),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ILUSTRACIÓN dentro de una Card
        Card(
            modifier = Modifier.size(width = 200.dp, height = 260.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = "Ilustración de $valor",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // VALOR CUALITATIVO
        Text(
            text = valor,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = colorValor
        )

        // Empuja el botón hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        // BOTÓN REGRESAR
        Button(
            onClick = onRegresar,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("REGRESAR")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultadoPreview() {
    ResultadoScreen(
        persona = Persona("Pacheco", "Hombre", 75.0, 178.0),
        onRegresar = {}
    )
}