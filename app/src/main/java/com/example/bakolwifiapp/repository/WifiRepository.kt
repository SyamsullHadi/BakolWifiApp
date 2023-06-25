package com.example.bakolwifiapp.repository

import com.example.bakolwifiapp.dao.WifiDao
import com.example.bakolwifiapp.model.Wifi
import kotlinx.coroutines.flow.Flow

class WifiRepository(private val wifiDao: WifiDao) {
    val allWifi: Flow<List<Wifi>> = wifiDao.getAllWifi()
    suspend fun insertWifi(wifi: Wifi) {
        wifiDao.insertWifi(wifi)
    }
    suspend fun deleteWifi(wifi: Wifi) {
        wifiDao.deleteWifi(wifi)
    }
    suspend fun updateWifi(wifi: Wifi) {
        wifiDao.updateWifi (wifi)
    }
}