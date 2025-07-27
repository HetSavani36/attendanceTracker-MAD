package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopAppBar(text: String) {
    Column(modifier = Modifier.height(100.dp).fillMaxWidth().background(color = Color.Blue)){
        Spacer(modifier = Modifier.size(50.dp))
        Text(text, fontSize = 25.sp, fontWeight = FontWeight.SemiBold, color = Color.White, modifier = Modifier.padding(start = 20.dp))
    }
}