package com.prime.mm_kunyi.mvp.views

import com.prime.mm_kunyi.data.vo.JobListVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
interface JobDetailView : BaseView{
    fun navigateToJobApplyForm(jobID: Int)
}