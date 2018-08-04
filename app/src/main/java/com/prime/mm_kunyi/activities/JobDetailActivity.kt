package com.prime.mm_kunyi.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.mvp.presenters.JobDetailPresenter
import com.prime.mm_kunyi.mvp.views.JobDetailView
import kotlinx.android.synthetic.main.activity_detail_content.*
import kotlinx.android.synthetic.main.activity_job_detail.*

class JobDetailActivity : BaseActivity(), JobDetailView {

    override fun getlayoutRes(): Int {
        return R.layout.activity_job_detail
    }

    companion object {
        private const val IE_JOB_ID: String = "IE_JOB_ID"

        fun getJobDetailActivityIntent(context: Context, extraJobID: Int): Intent {
            val intent = Intent(context, JobDetailActivity::class.java)
            intent.putExtra(IE_JOB_ID, extraJobID)
            return intent
        }
    }

    private lateinit var mPresenter: JobDetailPresenter

    override fun setUpContents(savedInstanceState: Bundle?) {
        setUpToolbar(true)
        setUpToolbarText("Job Details")

        mPresenter = ViewModelProviders.of(this).get(JobDetailPresenter::class.java)
        mPresenter.initPresenter(this)

        mPresenter.onUiReady(intent.getIntExtra(IE_JOB_ID, 0)).observe(this,
                Observer<JobListVO> { jobList: JobListVO? ->
                    bindDataToView(jobList)
                })

    }

    private fun bindDataToView(job: JobListVO?) {

        tvJDShortDescription.text = job!!.shortDesc
        tvAvailablePostCount.text = job!!.availablePostCount.toString()
        tvJDFullDescription.text = job!!.fullDesc
        tvJDLocation.text = job!!.location
        tvJDPrice.text = job!!.offerAmount!!.amount.toString() + " MMK"

        tvApplyJob.setOnClickListener { mPresenter.onTapApplyJob(jobID = job.jobPostId!!) }
    }


    override fun navigateToJobApplyForm(jobID: Int) {
        startActivity(ApplyJobActivity.getApplyJobActivityIntent(this, jobID))
    }
}
