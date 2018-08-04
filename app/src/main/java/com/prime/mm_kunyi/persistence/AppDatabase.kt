package com.prime.mm_kunyi.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.persistence.dao.JobListDao
import com.prime.mm_kunyi.utils.AppConstants

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
@Database(entities = [(JobListVO::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DB_NAME)
                        .allowMainThreadQueries()
                        .build()
            }
            val i = INSTANCE
            return i!!
        }
    }

    // define DAOs here ...
    abstract fun jobListDao(): JobListDao
}