package com.prime.mm_kunyi.mvp.presenters

import android.arch.lifecycle.LiveData
import com.prime.mm_kunyi.data.models.JobListModel
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.mvp.views.JobDetailView

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class JobDetailPresenter : BasePresenter<JobDetailView>() {

    override fun initPresenter(mView: JobDetailView) {
        super.initPresenter(mView)
    }

    fun onUiReady(jobID: Int): LiveData<JobListVO> {
        return JobListModel.getInstance().getJobById(jobID)
    }

    fun onTapApplyJob(jobID: Int){
        mView.navigateToJobApplyForm(jobID)
    }
}