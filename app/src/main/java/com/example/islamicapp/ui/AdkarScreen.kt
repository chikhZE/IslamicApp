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
import com.example.islamicapp.data.categories
import com.example.islamicapp.ui.theme.SecondColor



@Composable
fun AdkarScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    adkarViewModel: AdkarViewModel = viewModel(),

    ) {
    val categoriesList by adkarViewModel.categories.collectAsState()

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
                    categoryName,
                    matchingIcon,
                    {
                        val encodedTitle = Uri.encode(categoryName)
                        navController.navigate("dikr_screen/$encodedTitle")}
                )
            }
        }
    }
}

@Composable
fun AdkarItem(
    title: String = "أذكار الصباح",
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(1.dp)
            .clickable(true) {
                onClick()
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
