package com.prime.mm_kunyi.mvp.views

import com.prime.mm_kunyi.data.vo.JobListVO

/**
 * Created by yepyaesonetun on 8/4/18.
 **/
interface PostNewJobView: BaseView {

    fun collectRequiredData(newJobID: String): JobListVO
    fun addJobProcess()
}