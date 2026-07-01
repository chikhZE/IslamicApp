package com.example.islamicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.islamicapp.data.AdkarViewModel
import com.example.islamicapp.ui.AdkarScreen
import com.example.islamicapp.ui.AllHadithScreen
import com.example.islamicapp.ui.BottomNavigationBar
import com.example.islamicapp.ui.DikrScreen
import com.example.islamicapp.ui.HadithScreen
import com.example.islamicapp.ui.HomeScreen
import com.example.islamicapp.ui.theme.IslamicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IslamicAppTheme {
                myApp()
            }
        }
    }
}

@Composable
fun myApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val adkarViewModel: AdkarViewModel = viewModel()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            bottomBar = {
                if(currentRoute?.startsWith("dikr_screen") != true && currentRoute?.startsWith("hadith_screen") != true ) BottomNavigationBar(navController)

            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home_screen"
            ) {
                composable("home_screen") {
                    HomeScreen(
                        Modifier.padding(innerPadding)
                    )
                }
                composable("allhadith_screen") {
                    AllHadithScreen(
                        navController,
                        Modifier.padding(innerPadding)
                    )
                }
                composable("hadith_screen") {
                    HadithScreen()
                }
                composable("adkar_screen") {
                    AdkarScreen(
                        navController,
                        Modifier.padding(innerPadding),
                        adkarViewModel
                    )

                }
                composable("dikr_screen/{dikrName}") { backStackEntry  ->
                    val encodedName = backStackEntry.arguments?.getString("dikrName") ?: ""
                    val dikrName = java.net.URLDecoder.decode(encodedName, "UTF-8")
                    DikrScreen(
                        dikrName,
                        Modifier,
                        adkarViewModel
                    )
                }
            }

        }
    }
}
