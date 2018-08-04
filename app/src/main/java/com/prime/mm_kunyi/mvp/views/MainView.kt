package com.prime.mm_kunyi.mvp.views

/**
 * Created by yepyaesonetun on 8/1/18.
 **/
interface MainView : BaseView{

    // job list or home fragment
    fun navigateToJobDetail(jobID: Int)

    fun newJostPostProcess()

    fun navigateToJobPost()


    // profile fragment
    fun navigateToLogOut()
}