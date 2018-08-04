package com.prime.mm_kunyi.root

import android.app.Application
import com.google.firebase.FirebaseApp
import com.prime.mm_kunyi.data.models.JobListModel

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
class MMKuNyiApp :Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        JobListModel.initializeJobListModel(this)
    }
}