package com.example.bakolwifiapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bakolwifiapp.model.Wifi
import kotlinx.coroutines.flow.Flow

@Dao
interface WifiDao {
    @Query("SELECT * FROM 'wifi_table' ORDER BY name ASC")
    fun getAllWifi(): Flow<List<Wifi>>

    @Insert
    suspend fun  insertWifi(wifi: Wifi)

    @Delete
    suspend fun deleteWifi(wifi: Wifi)

    @Update fun updateWifi(wifi: Wifi)
}