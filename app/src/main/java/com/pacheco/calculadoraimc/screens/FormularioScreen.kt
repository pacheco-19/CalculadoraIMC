package com.pacheco.calculadoraimc.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacheco.calculadoraimc.model.*

@Composable
fun FormularioScreen(
    modifier: Modifier = Modifier,
    onCalcular: (Persona) -> Unit
) {
    // Lo que escribe el usuario
    var nombre by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var estatura by remember { mutableStateOf("") }

    // Mensajes de error (null = sin error)
    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorSexo by remember { mutableStateOf<String?>(null) }
    var errorPeso by remember { mutableStateOf<String?>(null) }
    var errorEstatura by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora IMC",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // NOMBRE
        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                errorNombre = null
            },
            label = { Text("Nombre") },
            singleLine = true,
            isError = errorNombre != null,
            supportingText = { errorNombre?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth()
        )

        // SEXO
        Text(
            text = "Sexo",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            RadioButton(
                selected = sexo == "Hombre",
                onClick = {
                    sexo = "Hombre"
                    errorSexo = null
                }
            )
            Text("Hombre", color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(24.dp))
            RadioButton(
                selected = sexo == "Mujer",
                onClick = {
                    sexo = "Mujer"
                    errorSexo = null
                }
            )
            Text("Mujer", color = MaterialTheme.colorScheme.primary)
        }
        errorSexo?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // PESO
        OutlinedTextField(
            value = peso,
            onValueChange = {
                peso = it
                errorPeso = null
            },
            label = { Text("Peso") },
            suffix = { Text("kg") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = errorPeso != null,
            supportingText = { errorPeso?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth()
        )

        // ESTATURA
        OutlinedTextField(
            value = estatura,
            onValueChange = {
                estatura = it
                errorEstatura = null
            },
            label = { Text("Estatura") },
            suffix = { Text("cm") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = errorEstatura != null,
            supportingText = { errorEstatura?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // BOTÓN CALCULAR
        Button(
            onClick = {
                // Validamos todo
                errorNombre = validarNombre(nombre)
                errorSexo = validarSexo(sexo)
                errorPeso = validarPeso(peso)
                errorEstatura = validarEstatura(estatura)

                // Si no hay ningún error, pasamos a la pantalla 2
                if (errorNombre == null && errorSexo == null &&
                    errorPeso == null && errorEstatura == null
                ) {
                    val persona = Persona(
                        nombre = nombre.trim(),
                        sexo = sexo,
                        peso = aNumero(peso)!!,
                        estatura = aNumero(estatura)!!
                    )
                    onCalcular(persona)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("CALCULAR IMC")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioPreview() {
    FormularioScreen(onCalcular = {})
}