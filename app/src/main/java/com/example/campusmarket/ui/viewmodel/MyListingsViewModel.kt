package com.example.campusmarket.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.campusmarket.data.local.AppDatabase
import com.example.campusmarket.data.local.ThemePreferencesRepository
import com.example.campusmarket.data.local.UserListingEntity
import com.example.campusmarket.data.repository.LocalListingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyListingsViewModel(application: Application) : AndroidViewModel(application) {

    private val localRepository: LocalListingRepository
    private val themeRepository: ThemePreferencesRepository

    val listings: StateFlow<List<UserListingEntity>>
    val isDarkTheme: StateFlow<Boolean>

    init {
        val dao = AppDatabase.getDatabase(application).listingDao()
        localRepository = LocalListingRepository(dao)
        themeRepository = ThemePreferencesRepository(application)

        listings = localRepository.allListings.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        isDarkTheme = themeRepository.isDarkTheme.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )
    }

    fun addListing(
        title: String,
        price: Double,
        category: String,
        condition: String,
        location: String,
        description: String
    ) {
        viewModelScope.launch {
            localRepository.insertListing(
                UserListingEntity(
                    title = title.trim(),
                    price = price,
                    category = category.trim(),
                    condition = condition.trim(),
                    location = location.trim(),
                    description = description.trim()
                )
            )
        }
    }

    fun updateListing(listing: UserListingEntity) {
        viewModelScope.launch {
            localRepository.updateListing(listing)
        }
    }

    fun deleteListing(listing: UserListingEntity) {
        viewModelScope.launch {
            localRepository.deleteListing(listing)
        }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            themeRepository.setDarkTheme(enabled)
        }
    }
}
