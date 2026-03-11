package com.example.calculadoraimc

import android.R.attr.name
import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        // HEADER
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.primary_color)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "IMC logo",
                modifier = Modifier
                    .size(80.dp)
                    .padding(vertical = 16.dp)
            )
            Text(
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                text = "Calculadora IMC"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            // FORM
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column() {
                    Text(text = "Seus dados")
                    Button(
                        onClick = {}
                    ) {
                        Text(
                            text = "CALCULAR"
                        )
                    }
                }
            }
            // RESULT
            Card() {
                Row() {
                    Text(text = "")
                    Text(text = "")
                }
            }
        }
    }
}