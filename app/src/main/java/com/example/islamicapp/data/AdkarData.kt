package com.example.islamicapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dikrData(
    @SerialName("category") val category: String,
    @SerialName("count") val count: String,
    @SerialName("description") val description: String,
    @SerialName("reference") val reference: String,
    @SerialName("content") val content: String
)
@Serializable
data class hadithData(
    val hadith: String,
    val description: String,
)
data class AdkarCategory(
    val title: String,
    val icon: ImageVector
)

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
