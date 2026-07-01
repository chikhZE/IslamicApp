package com.example.islamicapp.ui

import android.graphics.drawable.Icon
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.islamicapp.data.AdkarViewModel
import com.example.islamicapp.ui.theme.SecondColor

data class AdkarCategory(
    val title: String,
    val icon: ImageVector
)

@Composable
fun AdkarScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    adkarViewModel: AdkarViewModel = viewModel(),

    ) {
    val categoriesList by adkarViewModel.categories.collectAsState()
    val categories = listOf(
        AdkarCategory("أذكار الصباح", Icons.Default.WbSunny),
        AdkarCategory("أذكار المساء", Icons.Default.NightsStay),
        AdkarCategory("أذكار بعد السلام من الصلاة المفروضة", Icons.Default.Mosque),
        AdkarCategory("أذكار النوم", Icons.Default.Bedtime),
        AdkarCategory("أذكار الاستيقاظ", Icons.Default.AccessAlarm),
        AdkarCategory("أدعية قرآنية", Icons.AutoMirrored.Filled.MenuBook),
        AdkarCategory("تسابيح", Icons.Default.Money),
        AdkarCategory("أدعية الأنبياء", Icons.Default.Flare),
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
            items(categoriesList) { categoryName ->
                val matchingIcon = categories.find { it.title == categoryName }?.icon ?: Icons.Default.Bedtime
                AdkarItem(
                    navController,
                    categoryName,
                    matchingIcon,
                )
            }
        }
    }
}

@Composable
fun AdkarItem(
    navController: NavHostController,
    title: String = "أذكار الصباح",
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(1.dp)
            .clickable(true) {
                val encodedTitle = Uri.encode(title)
                navController.navigate("dikr_screen/$encodedTitle")
            },
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
                imageVector = icon,
                contentDescription = null,
                tint = SecondColor,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )

        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun AdkarScreenPreview() {
    AdkarScreen()
}
*/