package com.example.islamicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.islamicapp.ui.HomeScreen
import com.example.islamicapp.ui.theme.IslamicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IslamicAppTheme {
                HomeScreen()
            }
        }
    }
}
