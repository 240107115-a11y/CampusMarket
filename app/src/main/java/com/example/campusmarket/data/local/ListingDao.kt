package com.example.campusmarket.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListing(listing: UserListingEntity): Long

    @Query("SELECT * FROM user_listings ORDER BY id DESC")
    fun getAllListings(): Flow<List<UserListingEntity>>

    @Query("SELECT * FROM user_listings WHERE id = :id")
    suspend fun getListingById(id: Int): UserListingEntity?

    @Update
    suspend fun updateListing(listing: UserListingEntity)

    @Delete
    suspend fun deleteListing(listing: UserListingEntity)
}
