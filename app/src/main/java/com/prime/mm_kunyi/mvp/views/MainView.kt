package com.prime.mm_kunyi.mvp.views

import com.prime.mm_kunyi.data.vo.JobListVO

/**
 * Created by yepyaesonetun on 8/1/18.
 **/
interface MainView : BaseView{

    // job list or home fragment
    fun navigateToJobDetail(jobID: Int)

    fun newJostPostProcess()

    fun navigateToJobPost()

    fun likeProcess(job: JobListVO, likeSequenceID: Int)
    fun commentProcess(job:JobListVO,commentSequenceID:Int, commentContent: String)


    // profile fragment
    fun navigateToLogOut()
}