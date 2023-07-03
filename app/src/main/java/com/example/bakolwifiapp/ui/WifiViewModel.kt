package com.example.bakolwifiapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bakolwifiapp.model.Wifi
import com.example.bakolwifiapp.repository.WifiRepository
import kotlinx.coroutines.launch

class WifiViewModel(private val repository: WifiRepository): ViewModel() {
    val  allWifi: LiveData<List<Wifi>> = repository.allWifi.asLiveData()

    fun insert(wifi: Wifi) = viewModelScope.launch {
        repository.insertWifi(wifi)
    }

    fun delete(wifi: Wifi) = viewModelScope.launch {
        repository.deleteWifi(wifi)
    }

    fun update(wifi: Wifi) = viewModelScope.launch {
        repository.updateWifi(wifi)
    }
}

class WifiViewModelFactory(private val repository: WifiRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((WifiViewModel::class.java))) {
            return WifiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}