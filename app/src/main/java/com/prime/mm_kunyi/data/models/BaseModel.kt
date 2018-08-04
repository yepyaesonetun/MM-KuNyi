package com.prime.mm_kunyi.data.models

import android.content.Context
import com.prime.mm_kunyi.persistence.AppDatabase

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
abstract class BaseModel protected constructor(context: Context){

    protected var mTheDB: AppDatabase = AppDatabase.getDatabase(context)
}