package com.pacheco.calculadoraimc.model

import com.pacheco.calculadoraimc.R

// ---------- LISTAS INMUTABLES DE ILUSTRACIONES ----------
// listOf crea una lista que NO se puede modificar
// Posición 0 = delgado, 1 = normal, 2 = gordo
val imagenesHombre: List<Int> = listOf(
    R.drawable.hombre_delgado,
    R.drawable.hombre_normal,
    R.drawable.hombre_gordo
)

val imagenesMujer: List<Int> = listOf(
    R.drawable.mujer_delgada,
    R.drawable.mujer_normal,
    R.drawable.mujer_gorda
)

// ---------- LÍMITES RAZONABLES ----------
const val PESO_MIN = 30.0
const val PESO_MAX = 250.0
const val ESTATURA_MIN = 100.0
const val ESTATURA_MAX = 230.0

// Convierte el texto a número (acepta 70.5 y 70,5). Si no es número devuelve null
fun aNumero(texto: String): Double? = texto.trim().replace(",", ".").toDoubleOrNull()

// ---------- CÁLCULO ----------
fun calcularImc(peso: Double, estaturaCm: Double): Double {
    val estaturaM = estaturaCm / 100
    return peso / (estaturaM * estaturaM)
}

fun valorCualitativo(imc: Double): String = when {
    imc < 18.5 -> "Peso bajo"
    imc < 25 -> "Peso normal"
    imc < 30 -> "Sobrepeso"
    else -> "Obesidad"
}

// Elige la posición de la lista (0, 1 o 2) según el IMC
fun posicionImagen(imc: Double): Int = when {
    imc < 18.5 -> 0
    imc < 25 -> 1
    else -> 2
}

// Devuelve la imagen según el sexo y el IMC
fun obtenerImagen(sexo: String, imc: Double): Int {
    val lista = if (sexo == "Hombre") imagenesHombre else imagenesMujer
    return lista[posicionImagen(imc)]
}

// ---------- VALIDACIONES ----------
// Cada una devuelve el mensaje de error, o null si está todo bien

fun validarNombre(nombre: String): String? =
    if (nombre.isBlank()) "Escribe tu nombre" else null

fun validarSexo(sexo: String): String? =
    if (sexo.isEmpty()) "Elige un sexo" else null

fun validarPeso(texto: String): String? {
    val peso = aNumero(texto)
    return when {
        texto.isBlank() -> "Escribe tu peso"
        peso == null -> "Escribe solo números"
        peso < PESO_MIN || peso > PESO_MAX -> "El peso debe estar entre 30 y 250 kg"
        else -> null
    }
}

fun validarEstatura(texto: String): String? {
    val estatura = aNumero(texto)
    return when {
        texto.isBlank() -> "Escribe tu estatura"
        estatura == null -> "Escribe solo números"
        estatura < ESTATURA_MIN || estatura > ESTATURA_MAX -> "La estatura debe estar entre 100 y 230 cm"
        else -> null
    }
}