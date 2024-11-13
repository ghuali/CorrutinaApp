package com.angel.corrutina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import kotlin.random.Random

@Composable
fun MainScreen() {
    var message by remember { mutableStateOf("Presiona el botón para iniciar") }


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
            // Ejecuta una corrutina al presionar el botón
            CoroutineScope(Job()).launch {
                message = "Adivinando número..."

                // Llama a la función asincrónica que usa Deferred
                val result = adivinacionNumero().await()

                message = result
            }
        }) {
            Text("Piensa un número del 1 al 10")
        }
    }
}


fun adivinacionNumero(): Deferred<String> = CoroutineScope(Job()).async {
    delay(3000)
    val randomNumber = Random.nextInt(1, 11)
    "¿Es el número $randomNumber?"
}
