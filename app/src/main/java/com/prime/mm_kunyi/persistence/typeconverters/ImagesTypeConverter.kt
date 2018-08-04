package com.prime.mm_kunyi.persistence.typeconverters

import android.arch.persistence.room.TypeConverter

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class ImagesTypeConverter {

    @TypeConverter
    fun toStringList(imagesCommaSeparated: String): List<String> {
        return imagesCommaSeparated.split(",".toRegex())
    }

    @TypeConverter
    fun toString(imageList: List<String>): String {
        val stringBuilder = StringBuilder()
        for (image in imageList) {
            stringBuilder.append(image).append(",")
        }
        if (stringBuilder.isNotEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length - 1)
        }
        return stringBuilder.toString()
    }

}