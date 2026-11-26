package com.example.campusmarket.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [UserListingEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listingDao(): ListingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "campus_market_database"
                )
                    .addCallback(DatabaseCallback(context.applicationContext))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                val dao = getDatabase(context).listingDao()
                dao.insertListing(
                    UserListingEntity(
                        title = "Discrete Math Notes & Solutions",
                        price = 15.0,
                        category = "Textbooks",
                        condition = "Like New",
                        location = "Library Floor 1",
                        description = "Printed and bound lecture notes with solved midterm problems."
                    )
                )
                dao.insertListing(
                    UserListingEntity(
                        title = "USB-C Multiport Hub",
                        price = 22.0,
                        category = "Electronics",
                        condition = "Good",
                        location = "Dormitory 1",
                        description = "HDMI, 3x USB 3.0, and SD card reader for MacBook and Windows laptops."
                    )
                )
            }
        }
    }
}
