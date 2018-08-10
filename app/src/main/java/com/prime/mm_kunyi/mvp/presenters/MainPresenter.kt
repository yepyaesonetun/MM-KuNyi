package com.prime.mm_kunyi.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.prime.mm_kunyi.data.models.JobListModel
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.delegates.HomeFragmentDelegate
import com.prime.mm_kunyi.delegates.JobItemDelegate
import com.prime.mm_kunyi.delegates.ProfileFragmentDelegate
import com.prime.mm_kunyi.mvp.views.MainView

/**
 * Created by yepyaesonetun on 8/1/18.
 **/
class MainPresenter : BasePresenter<MainView>(),
        JobItemDelegate,
        HomeFragmentDelegate,
        ProfileFragmentDelegate {


    private var mJobsLD: MutableLiveData<List<JobListVO>> = MutableLiveData()

    fun getJobsLD(): MutableLiveData<List<JobListVO>> {
        return mJobsLD
    }

    fun onUiReady() {
        JobListModel.getInstance().loadJobsFeed(mJobsLD, mErrorLD)
    }

    override fun onTapJobItem(id: Int) {
        mView.navigateToJobDetail(id)
    }

    override fun onTapLike(job: JobListVO, likeSequenceID: Int) {
        mView.likeProcess(job, likeSequenceID)
    }

    override fun onTapComment(job: JobListVO,commentSequenceID:Int, commentContent: String) {
        mView.commentProcess(job, commentSequenceID, commentContent)
    }

    override fun onTapFabNewJobPost() {
        mView.newJostPostProcess()
    }

    override fun onTapLogOut() {
        mView.navigateToLogOut()
    }

}