package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prime.mm_kunyi.data.vo.RequiredSkillVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class RequiredSkillTypeConverter {
    @TypeConverter
    fun toString(requiredSkillList: List<RequiredSkillVO>): String {
        return Gson().toJson(requiredSkillList)
    }

    @TypeConverter
    fun toRequiredSkill(requiredSkillJson: String): List<RequiredSkillVO> {
        val listType = object : TypeToken<List<RequiredSkillVO>>() {}.type
        return Gson().fromJson(requiredSkillJson, listType)
    }
}