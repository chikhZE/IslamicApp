package com.example.islamicapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.islamicapp.data.ayahHomeScreen
import com.example.islamicapp.data.duaHomeScreen
import com.example.islamicapp.data.hadithHomeScreen
import com.example.islamicapp.ui.theme.FirstColor
import com.example.islamicapp.ui.theme.SecondColor


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val currentDayOfYear = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_YEAR)
    val currentAyah by rememberSaveable { mutableStateOf(ayahHomeScreen[kotlin.random.Random(currentDayOfYear).nextInt(ayahHomeScreen.size)]) }
    val currentHadith by rememberSaveable { mutableStateOf(hadithHomeScreen[kotlin.random.Random(currentDayOfYear+1).nextInt(hadithHomeScreen.size)]) }
    val currentDuaa by rememberSaveable { mutableStateOf(duaHomeScreen[kotlin.random.Random(currentDayOfYear+2).nextInt(duaHomeScreen.size)]) }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "الجامع الإسلامي",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "السبت 11 محرم 1448",
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                InfoCard(
                    title = "حديث اليوم",
                    content = currentHadith
                )

                Spacer(modifier = Modifier.height(16.dp))

                InfoCard(
                    title = "آية اليوم",
                    content = currentAyah
                )

                Spacer(modifier = Modifier.height(16.dp))

                InfoCard(
                    title = "دعاء اليوم",
                    content = currentDuaa
                )
            }


}

@Composable
fun InfoCard(title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SecondColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = content,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
        }
    }
}
@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        containerColor = FirstColor,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Settings, contentDescription = null, tint = Color.White) },
            label = { Text("الإعدادات", color = Color.White, fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = SecondColor
            )
        )
        NavigationBarItem(
            selected = currentRoute == "adkar_screen",
            onClick = {
                if(currentRoute != "adkar_screen") {
                    navController.navigate("adkar_screen"){
                        popUpTo("home_screen") { inclusive = false }
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.White) },
            label = { Text("الأدعية", color = Color.White, fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = SecondColor
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = Color.White) },
            label = { Text("الأحاديث", color = Color.White, fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = SecondColor
            )
        )
        NavigationBarItem(
            selected = currentRoute == "home_screen",
            onClick = {
                if(currentRoute != "home_screen") {
                    navController.navigate("home_screen"){
                        popUpTo("home_screen") { inclusive = false }
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = null, tint = Color.White) },
            label = { Text("الرئيسية", color = Color.White, fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = SecondColor
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
