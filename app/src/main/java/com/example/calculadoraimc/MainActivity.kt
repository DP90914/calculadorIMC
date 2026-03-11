package com.example.calculadoraimc

import android.R.attr.label
import android.R.attr.name
import android.R.attr.onClick
import android.R.attr.text
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlin.text.toDouble

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
    var valorPeso by remember {
        mutableStateOf("")
    }
    var valorAltura by remember {
        mutableStateOf("")
    }
    var imc: Number by remember {
        mutableStateOf(0)
    }
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),

                ){
                    Text(
                        color = colorResource(R.color.primary_color),
                        fontWeight = FontWeight.Bold,
                        text = "Seus dados"
                    )
                    OutlinedTextField(
                        value = valorPeso,
                        onValueChange = {
                            novoValor ->
                            valorPeso = novoValor
                        },
                        shape = RoundedCornerShape(15.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorResource(R.color.primary_color),
                            unfocusedBorderColor = colorResource(R.color.primary_color),
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        ),
                        label = {
                            Text("Peso")
                        }
                    )
                    OutlinedTextField(
                        shape = RoundedCornerShape(15.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorResource(R.color.primary_color),
                            unfocusedBorderColor = colorResource(R.color.primary_color),
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        ),

                        value = valorAltura,
                        onValueChange = {
                                novoValor ->
                            valorAltura = novoValor
                        },
                        label = {
                            Text("Altura")
                        }
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.primary_color),
                        ),
                        onClick = {
                             imc = valorPeso.toDouble() / (valorAltura.toDouble() * valorAltura.toDouble())
                        }
                    ) {
                        Text(
                            text = "CALCULAR"
                        )
                    }
                }
            }
            // RESULT
            if(imc!=0) {
                CreateResult(modifier = Modifier, imc)
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                onClick = {
                    valorAltura = ""
                    valorPeso = ""
                }
            ) {
                Text(
                    text = "Limpar dados"
                )
            }
        }
    }
}

@Composable
fun CreateResult(modifier: Modifier = Modifier, imc: Number){
    var limitador = DecimalFormat("#.00")
    if (imc.toDouble()<18.5){
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    color = Color.White,
                    text = "${limitador.format(imc)}"
                )
                Text(
                    color = Color.White,
                    text = "Abaixo do peso"
                )
            }
        }
    }else if(imc.toDouble()>18.5 && imc.toDouble()<25){
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    color = Color.White,
                    text = "${limitador.format(imc)}"
                )
                Text(
                    color = Color.White,
                    text = "Peso ideal"
                )
            }
        }
    }else if(imc.toDouble()>=25 && imc.toDouble()<30){
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    color = Color.White,
                    text = "${limitador.format(imc)}"
                )
                Text(
                    color = Color.White,
                    text = "Pouco acima do peso"
                )
            }
        }
    }else if(imc.toDouble()>=30 && imc.toDouble()<35){
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    color = Color.White,
                    text = "${limitador.format(imc)}"
                )
                Text(
                    color = Color.White,
                    text = "Obesidade grau I"
                )
            }
        }
    }else if(imc.toDouble()>=35 && imc.toDouble()<40){
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    color = Color.White,
                    text = "${limitador.format(imc)}"
                )
                Text(
                    color = Color.White,
                    text = "Obesidade grau II"
                )
            }
        }
    }else{
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    color = Color.White,
                    text = "${limitador.format(imc)}"
                )
                Text(
                    color = Color.White,
                    text = "Obesidade grau III"
                )
            }
        }
    }
}