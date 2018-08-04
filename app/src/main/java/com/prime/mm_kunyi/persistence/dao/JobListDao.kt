package com.prime.mm_kunyi.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.prime.mm_kunyi.data.vo.JobListVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
@Dao
interface JobListDao {
    @Query("SELECT * FROM jobList")
    fun getJobList(): LiveData<List<JobListVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<JobListVO>): LongArray

    @Query("SELECT * FROM jobList WHERE jobPostId = :jobPostId")
    fun getJobById(jobPostId:Int):LiveData<JobListVO>

    @Query("SELECT * FROM jobList WHERE jobPostId = :jobPostId")
    fun getJobByIdLD(jobPostId:Int):LiveData<JobListVO>
}