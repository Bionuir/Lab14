package com.example.lab14

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab14.ui.theme.Lab14Theme
import kotlinx.coroutines.delay

class SecondScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab14Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun SecondScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Pantalla secundaria",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OscillatingColorSquare()
    }
}

@Composable
fun OscillatingColorSquare() {
    var color by remember { mutableStateOf(ComposeColor.Red) }
    var offset by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            color = getNextColor(color)
            for (i in 0..100) {
                offset = (i - 50).toFloat() // Oscila de -50 a 50
                delay(10) // Cambia la velocidad de oscilación
            }
            for (i in 100 downTo 0) {
                offset = (i - 50).toFloat() // Oscila de 50 a -50
                delay(10) // Cambia la velocidad de oscilación
            }
        }
    }

    Canvas(modifier = Modifier.size(100.dp).offset(offset.dp, 0.dp)) {
        drawRect(color = color)
    }
}

fun getNetColor(currentColor: ComposeColor): ComposeColor {
    return when (currentColor) {
        ComposeColor.Red -> ComposeColor.Yellow
        ComposeColor.Yellow -> ComposeColor.Green
        ComposeColor.Green -> ComposeColor.Cyan
        ComposeColor.Cyan -> ComposeColor.Blue
        ComposeColor.Blue -> ComposeColor.Magenta
        ComposeColor.Magenta -> ComposeColor.Red
        else -> ComposeColor.Red
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen()
}
