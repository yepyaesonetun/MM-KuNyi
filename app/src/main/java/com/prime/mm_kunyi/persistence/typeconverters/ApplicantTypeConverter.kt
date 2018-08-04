package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prime.mm_kunyi.data.vo.ApplicantVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class ApplicantTypeConverter {

    @TypeConverter
    fun toString(favoriteActions: List<ApplicantVO>): String {
        return Gson().toJson(favoriteActions)
    }

    @TypeConverter
    fun toApplicantActions(ApplicantJson: String): List<ApplicantVO> {
        val listType = object : TypeToken<List<ApplicantVO>>() {}.type
        return Gson().fromJson(ApplicantJson, listType)
    }
}