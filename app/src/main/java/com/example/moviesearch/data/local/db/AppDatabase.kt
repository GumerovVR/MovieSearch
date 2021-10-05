package com.example.moviesearch.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesearch.data.local.entities.MovieDB

@Database(entities = [MovieDB::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        operator fun invoke(context: Context)
        = db ?: synchronized(LOCK) {
            db ?: getInstance(context).also { db = it }
        }

        private fun getInstance(context: Context)
        = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
    abstract fun getFavouriteMovieDao(): FavouriteMovieDao
}
