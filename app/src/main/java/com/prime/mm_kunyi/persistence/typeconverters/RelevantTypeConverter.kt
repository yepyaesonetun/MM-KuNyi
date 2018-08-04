package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prime.mm_kunyi.data.vo.RelevantVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class RelevantTypeConverter {

    @TypeConverter
    fun toString(relevantList: List<RelevantVO>): String {
        return Gson().toJson(relevantList)
    }

    @TypeConverter
    fun toRelevant(relevantJson: String): List<RelevantVO> {
        val listType = object : TypeToken<List<RelevantVO>>() {}.type
        return Gson().fromJson(relevantJson, listType)
    }
}