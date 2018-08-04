package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prime.mm_kunyi.data.vo.JobTagsVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class JobTagTypeConverter {
    @TypeConverter
    fun toString(jobTagList: List<JobTagsVO>): String {
        return Gson().toJson(jobTagList)
    }

    @TypeConverter
    fun toJobTags(jobTagsJson: String): List<JobTagsVO> {
        val listType = object : TypeToken<List<JobTagsVO>>() {}.type
        return Gson().fromJson(jobTagsJson, listType)
    }
}