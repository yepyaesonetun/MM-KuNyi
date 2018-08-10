package com.prime.mm_kunyi.mvp.presenters

import android.arch.lifecycle.LiveData
import com.prime.mm_kunyi.data.models.JobListModel
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.mvp.views.ApplyJobView

/**
 * Created by yepyaesonetun on 8/4/18.
 **/
class ApplyJobPresenter: BasePresenter<ApplyJobView>() {

    override fun initPresenter(mView: ApplyJobView) {
        super.initPresenter(mView)
    }

    fun onUiReady(jobID: Int): LiveData<JobListVO> {
        return JobListModel.getInstance().getJobById(jobID)
    }

    fun onTapApplyJob(job: JobListVO){
        mView.navigateSendJob(job)
    }

}