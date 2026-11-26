package com.example.campusmarket.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_listings")
data class UserListingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val price: Double,
    val category: String,
    val condition: String,
    val location: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)
