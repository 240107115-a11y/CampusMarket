package com.example.campusmarket.data.repository

import com.example.campusmarket.data.local.ListingDao
import com.example.campusmarket.data.local.UserListingEntity
import kotlinx.coroutines.flow.Flow

class LocalListingRepository(private val listingDao: ListingDao) {

    val allListings: Flow<List<UserListingEntity>> = listingDao.getAllListings()

    suspend fun insertListing(listing: UserListingEntity) {
        listingDao.insertListing(listing)
    }

    suspend fun updateListing(listing: UserListingEntity) {
        listingDao.updateListing(listing)
    }

    suspend fun deleteListing(listing: UserListingEntity) {
        listingDao.deleteListing(listing)
    }
}
