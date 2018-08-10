package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prime.mm_kunyi.data.vo.CommentVO

/**
 * Created by yepyaesonetun on 8/10/18.
 **/
class CommentTypeConverter {
    @TypeConverter
    fun toString(commentList: List<CommentVO>): String {
        return Gson().toJson(commentList)
    }

    @TypeConverter
    fun toComment(commentsJson: String): List<CommentVO> {
        val listType = object : TypeToken<List<CommentVO>>() {}.type
        return Gson().fromJson(commentsJson, listType)
    }
}