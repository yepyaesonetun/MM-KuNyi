package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prime.mm_kunyi.data.vo.InterestedVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class InterestedTypeConverter {
    @TypeConverter
    fun toString(interestedList: List<InterestedVO>): String {
        return Gson().toJson(interestedList)
    }

    @TypeConverter
    fun toInterested(interestedJson: String): List<InterestedVO> {
        val listType = object : TypeToken<List<InterestedVO>>() {}.type
        return Gson().fromJson(interestedJson, listType)
    }
}