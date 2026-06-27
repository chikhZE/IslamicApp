package com.example.islamicapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicapp.ui.theme.FirstColor
import com.example.islamicapp.ui.theme.SecondColor

data class AdkarCategory(
    val title: String,
    val icon: ImageVector
)

@Composable
fun AdkarScreen(modifier: Modifier = Modifier) {
    val categories = listOf(
        AdkarCategory("أذكار الصباح", Icons.Default.WbSunny),
        AdkarCategory("أذكار المساء", Icons.Default.NightsStay),
        AdkarCategory("أذكار بعد الصلاة", Icons.Default.Mosque),
        AdkarCategory("أذكار النوم", Icons.Default.Bedtime),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "الأذكار الشرعية",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                AdkarItem(category,Modifier.clickable(true){})
            }
        }
    }
}

@Composable
fun AdkarItem(category: AdkarCategory, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(1.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = null,
                tint = SecondColor,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = category.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdkarScreenPreview() {
    AdkarScreen()
}
