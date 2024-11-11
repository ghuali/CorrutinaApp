package com.angel.corrutina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var message by remember { mutableStateOf("Presiona el botón para iniciar") }

    // Estructura de la UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            CoroutineScope(Dispatchers.Main).launch {
                message = "Adivinando numero..."

                // Llama a la función asincrónica que usa Deferred
                val result = adivinacionNumero().await()

                message = result
            }
        }) {
            Text("Piensa un número del 1 al 10")
        }
    }
}

// Función que simula la adivinación de un número aleatorio
private fun adivinacionNumero(): Deferred<String> = CoroutineScope(Dispatchers.IO).async {
    delay(3000)
    val randomNumber = Random.nextInt(1, 11) // Genera un número aleatorio entre 1 y 10
    "¿Es el número $randomNumber?"
}
