package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prime.mm_kunyi.data.vo.ViewedVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class ViewedTypeConverter {
    @TypeConverter
    fun toString(viewedList: List<ViewedVO>): String {
        return Gson().toJson(viewedList)
    }

    @TypeConverter
    fun toViewd(viewedList: String): List<ViewedVO> {
        val viewType = object : TypeToken<List<ViewedVO>>() {}.type
        return Gson().fromJson(viewedList, viewType)
    }
}