package com.example.lab14

import android.graphics.Color
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab14Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Página principal",
            style = MaterialTheme.typography.titleLarge // Usa un estilo de texto apropiado
        )

        Spacer(modifier = Modifier.height(16.dp))

        ColorChangingCircle()
    }
}

@Composable
fun ColorChangingCircle() {
    var color by remember { mutableStateOf(ComposeColor.Red) }

    LaunchedEffect(Unit) {
        while (true) {
            color = getNextColor(color)
            delay(500) // Cambiar de color cada 500 ms
        }
    }

    Canvas(modifier = Modifier.size(100.dp)) {
        drawCircle(color)
    }
}

fun getNextColor(currentColor: ComposeColor): ComposeColor {
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
fun MainScreenPreview() {
    Lab14Theme {
        MainScreen()
    }
}
