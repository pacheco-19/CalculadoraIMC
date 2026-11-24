package com.pacheco.calculadoraimc.model

// Guarda los datos que escribe el usuario en el formulario
data class Persona(
    val nombre: String,
    val sexo: String,      // "Hombre" o "Mujer"
    val peso: Double,      // en kg
    val estatura: Double   // en cm
)