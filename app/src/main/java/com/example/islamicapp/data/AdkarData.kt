package com.example.islamicapp.data

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