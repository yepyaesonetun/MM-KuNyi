package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prime.mm_kunyi.data.vo.LikeVO

/**
 * Created by yepyaesonetun on 8/10/18.
 **/
class LikeTypeConverter {
    @TypeConverter
    fun toString(likeList: List<LikeVO>): String {
        return Gson().toJson(likeList)
    }

    @TypeConverter()
    fun toLike(likesJson: String): List<LikeVO> {
        val listType = object : TypeToken<List<LikeVO>>() {}.type
        return Gson().fromJson(likesJson, listType)
    }
}