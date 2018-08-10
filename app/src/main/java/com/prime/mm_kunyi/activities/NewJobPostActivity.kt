package com.prime.mm_kunyi.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.data.models.JobListModel
import com.prime.mm_kunyi.data.vo.JobDurationVO
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.data.vo.OfferAmount
import com.prime.mm_kunyi.data.vo.RequiredSkillVO
import com.prime.mm_kunyi.mvp.presenters.PostNewJobPresenter
import com.prime.mm_kunyi.mvp.views.PostNewJobView
import kotlinx.android.synthetic.main.activity_job_post.*

class NewJobPostActivity : BaseActivity(), PostNewJobView {

    override fun getlayoutRes(): Int {
        return R.layout.activity_job_post
    }

    companion object {

        private const val IE_NEW_JOB_ID: String = "IE_NEW_JOB_ID"
        fun getNewJobPostActivityIntent(context: Context, extraJobID: Int): Intent {
            val intent = Intent(context, NewJobPostActivity::class.java)
            intent.putExtra(IE_NEW_JOB_ID, extraJobID)
            return intent
        }
    }

    private lateinit var mPresenter: PostNewJobPresenter
    private var jobID: Int? = null

    override fun setUpContents(savedInstanceState: Bundle?) {
        setUpToolbar(true)
        setUpToolbarText("Post New Job")

        jobID = intent.getIntExtra(IE_NEW_JOB_ID, 0)

        mPresenter = ViewModelProviders.of(this)
                .get(PostNewJobPresenter::class.java)
        mPresenter.initPresenter(this)

        tvPostNewJob.setOnClickListener { mPresenter.onTapPost() }
    }

    override fun collectRequiredData(newJobID: String): JobListVO {

        val newJob = JobListVO()
        newJob.jobPostId = newJobID.toInt()
        newJob.images = listOf("abc", "abc", "abc")
        newJob.shortDesc = edtShortDescription.text.toString()
        newJob.fullDesc = edtFullDescription.text.toString()
        newJob.location = edtJobLocation.text.toString()
        newJob.email = edtEmail.text.toString()
        newJob.phoneNo = edtPhoneNumber.text.toString()
        newJob.postClosedDate = edtPostClosedDate.text.toString()
        newJob.postedDate = edtPostDate.text.toString()
        newJob.offerAmount = OfferAmount(edtOfferDuration.text.toString(), edtOfferAmount.text.toString().toInt())
        newJob.jobDuration = JobDurationVO("", "", edtWorkingHourPerDay.text.toString().toInt(),
                edtWorkingDayPerWeek.text.toString().toInt(), edtTotalWorkingDay.text.toString().toInt())
        newJob.requiredSkill = listOf(RequiredSkillVO(edtRequiredSkill.text.toString(), newJobID.toInt()))
        newJob.importantNotes = listOf(edtImportantNotes.text.toString())

        return newJob
    }

    override fun addJobProcess() {
        JobListModel.getInstance().addNewJob(jobID.toString(), collectRequiredData(jobID.toString()),
                object : JobListModel.AddNewJobDelegate {
                    override fun onSuccessMsg(successMsg: String) {
                        Snackbar.make(svNewPost, successMsg, Snackbar.LENGTH_SHORT).show()
                        finish()
                    }

                })
    }

}
