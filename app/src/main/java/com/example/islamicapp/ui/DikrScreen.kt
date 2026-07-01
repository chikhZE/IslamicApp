package com.example.islamicapp.ui

import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.data.AdkarViewModel
import com.example.islamicapp.data.categories
import com.example.islamicapp.ui.theme.FirstColor
import com.example.islamicapp.ui.theme.SecondColor
import kotlinx.coroutines.flow.MutableStateFlow
private val GreenBar = Color(0xFF6ED05B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DikrScreen(
    dikrName: String,
    modifier: Modifier = Modifier,
    adkarViewModel: AdkarViewModel = viewModel(),

    ) {
    val dikrList by adkarViewModel.allAdkarMap.collectAsState()
    val currentDikrList = dikrList[dikrName] ?: emptyList()
    Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
                        ) {
                            Text(
                                dikrName,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Icon(
                                imageVector = categories.find { it.title == dikrName }?.icon ?: Icons.Default.Bedtime,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.7f),
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = FirstColor
                    )
                )
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
            ) {
                items(currentDikrList) { dikr ->

                    DikrItem(
                        text = dikr.content,
                        count = dikr.count,
                        reference = dikr.reference

                    )
                }
            }
        }
    }

@Composable
fun DikrItem(
    text: String,
    count: String,
    reference: String = ""
) {
    val context = LocalContext.current
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = context.getSystemService(VibratorManager::class.java)
        manager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Vibrator::class.java)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        var countNum by rememberSaveable {mutableStateOf(count.toInt()) }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .clickable(true) {
                    if(countNum >0) countNum--
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        @Suppress("DEPRECATION")
                        vibrator.vibrate(150)
                    }
                }
            ,
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        )
        {
            Text(
                text = "$text$reference",
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                fontSize = 24.sp,
                lineHeight = 32.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF444444)
            )
        }

        Surface(
            modifier = Modifier
                .width(260.dp)
                .height(40.dp),
            shape = RoundedCornerShape(20.dp),
            color = SecondColor,
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {



                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            countNum.toString(),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "التكرار",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(20.dp)
                        .background(Color.White.copy(alpha = 0.5f))
                )
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable{
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, text)
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, "Share via")
                            context.startActivity(shareIntent)
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "مشاركة",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

            }
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun DikrScreenPreview() {
    DikrScreen()
}
*/