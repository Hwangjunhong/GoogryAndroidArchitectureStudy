package com.example.kyudong3.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieLocalEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private const val DB_NAME = "movie-db"
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                DB_NAME
            )
                .build()
        }
    }
}