package com.example.celsiustofahrenheitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TemperatureConverter()
        }
    }
}

@Composable
fun TemperatureConverter() {
    var celsius by remember { mutableStateOf(0f) }
    var fahrenheit by remember { mutableStateOf(32f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Celsius: ${celsius.toInt()}ºC",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Slider(
            value = celsius,
            onValueChange = {
                celsius = it
                fahrenheit = celsiusToFahrenheit(it)
            },
            valueRange = 0f..100f,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = "Fahrenheit: ${fahrenheit.toInt()}ºF",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Slider(
            value = fahrenheit,
            onValueChange = {
                fahrenheit = if (it < 32f) 32f else it
                celsius = fahrenheitToCelsius(fahrenheit)
            },
            valueRange = 0f..212f,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = if (celsius <= 20) "I wish it were warmer." else "I wish it were colder.",
            color = if (celsius <= 20) Color.Red else Color.Blue,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

fun celsiusToFahrenheit(celsius: Float): Float {
    return (celsius * 9 / 5) + 32
}

fun fahrenheitToCelsius(fahrenheit: Float): Float {
    return (fahrenheit - 32) * 5 / 9
}

@Preview(showBackground = true)
@Composable
fun TemperatureConverterPreview() {
    TemperatureConverter()
}
