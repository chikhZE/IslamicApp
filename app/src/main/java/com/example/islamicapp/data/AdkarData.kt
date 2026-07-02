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
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.time.format.DecimalStyle
import java.util.Locale

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

fun getCurrentIslamicDate(): String {
    val hijriDate = HijrahDate.now()
    return hijriDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy G", Locale("ar")))
}
@Entity(tableName = "azkar_count")
data class AzkarCounter(
    @PrimaryKey
    val id: Int = 1,
    val count: Int
)