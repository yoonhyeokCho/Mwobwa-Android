package com.bongku.mwobwa.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bongku.mwobwa.data.entity.SavedContentEntity

@Database(entities = [SavedContentEntity::class], version = 1)
abstract class SavedDatabase : RoomDatabase() {
    abstract fun savedDao(): SavedDao

    companion object {

        @Volatile
        private var INSTANCE: SavedDatabase? = null

        fun getDatabse(
            context: Context
        ): SavedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedDatabase::class.java,
                    "saved_content_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}