package com.example.pixelsapp.screens.home_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pixelsapp.ui.theme.Primary

@Composable
fun CategoryChip(text: String, isSelected: Boolean = false) {
    Surface(
        modifier = Modifier.padding(bottom = 8.dp),
        color = if (isSelected) Primary else Color(0xFFF5F5F5),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            color = if (isSelected) Color.White else Color.Black
        )
    }
}