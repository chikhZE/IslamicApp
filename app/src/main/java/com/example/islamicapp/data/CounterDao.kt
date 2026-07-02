package com.example.islamicapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {
    @Query("SELECT * FROM azkar_count WHERE id = 1")
    fun getCounter(): Flow<AzkarCounter?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCounter(counter: AzkarCounter)


}