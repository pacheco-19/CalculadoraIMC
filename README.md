# Calculadora IMC

Aplicación Android hecha con Kotlin, Jetpack Compose y Material 3 que calcula el Índice de Masa Corporal (IMC) de una persona a partir de su peso y su estatura.

## Funcionamiento

La aplicación tiene dos pantallas:

**Pantalla 1 - Formulario**

El usuario introduce su nombre, sexo (Hombre o Mujer), peso en kg y estatura en cm, y pulsa el botón CALCULAR IMC. Antes de calcular se validan los datos; si alguno es incorrecto, el campo se marca en rojo con un mensaje de error.

**Pantalla 2 - Resultado**

Muestra el nombre de la persona, su IMC con un decimal, una ilustración según el sexo y el resultado (delgado, normal o gordo), el valor cualitativo y un botón REGRESAR para volver al formulario.

## Validaciones

| Campo    | Regla                          |
|----------|--------------------------------|
| Nombre   | No puede estar vacío           |
| Sexo     | Hay que elegir Hombre o Mujer  |
| Peso     | Número entre 30 y 250 kg       |
| Estatura | Número entre 100 y 230 cm      |

Se aceptan decimales con punto o con coma (70.5 o 70,5).

## Cálculo del IMC

```
IMC = peso (kg) / estatura (m)²
```

| IMC           | Valor cualitativo | Ilustración |
|---------------|-------------------|-------------|
| Menos de 18,5 | Peso bajo         | Delgado/a   |
| 18,5 - 24,9   | Peso normal       | Normal      |
| 25 - 29,9     | Sobrepeso         | Gordo/a     |
| 30 o más      | Obesidad          | Gordo/a     |

## Estructura del proyecto

```
com.pacheco.calculadoraimc/
├── MainActivity.kt
├── model/
│   ├── Persona.kt
│   └── CalculadoraImc.kt
└── screens/
    ├── FormularioScreen.kt
    └── ResultadoScreen.kt
```

- **MainActivity.kt**: arranca la aplicación y decide qué pantalla se muestra. Usa una variable de estado `persona`: si es `null` se ve el formulario y si tiene datos se ve el resultado.
- **Persona.kt**: data class que guarda nombre, sexo, peso y estatura para pasar los datos de una pantalla a otra.
- **CalculadoraImc.kt**: contiene la lógica. Las dos listas inmutables (`listOf`) con las ilustraciones de hombre y mujer, la fórmula del IMC, el valor cualitativo y las funciones de validación.
- **FormularioScreen.kt**: pantalla del formulario con `OutlinedTextField`, `RadioButton` y el botón de calcular. Guarda los datos con `remember` y `mutableStateOf`.
- **ResultadoScreen.kt**: pantalla del resultado con el IMC, la ilustración dentro de una `Card`, el valor cualitativo y el botón de regreso.

Las ilustraciones están en `res/drawable`: `hombre_delgado`, `hombre_normal`, `hombre_gordo`, `mujer_delgada`, `mujer_normal` y `mujer_gorda`.

## Cómo ejecutarlo

1. Abrir el proyecto con Android Studio.
2. Esperar a que termine la sincronización de Gradle.
3. Ejecutar en un emulador o móvil con Android 7.0 (API 24) o superior.

## Tecnologías

- Kotlin
- Jetpack Compose
- Material 3
- Android Studio

## Autor

Alejandro Pacheco - DAM 2