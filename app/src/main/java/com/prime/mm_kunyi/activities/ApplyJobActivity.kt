package com.prime.mm_kunyi.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.mvp.presenters.ApplyJobPresenter
import com.prime.mm_kunyi.mvp.views.ApplyJobView
import com.prime.mm_kunyi.utils.GlideApp
import kotlinx.android.synthetic.main.activity_apply_job.*

class ApplyJobActivity : BaseActivity(), ApplyJobView {
    override fun getlayoutRes(): Int {
        return R.layout.activity_apply_job
    }

    companion object {
        private const val IE_JOB_ID = "IE_JOB_ID"
        fun getApplyJobActivityIntent(context: Context, extraJobID: Int): Intent {
            val intent = Intent(context, ApplyJobActivity::class.java)
            intent.putExtra(IE_JOB_ID, extraJobID)
            return intent
        }
    }

    private var canLowerOfferAmount: Boolean? = true

    private lateinit var mFirebaseUser: FirebaseUser
    private lateinit var mPresenter: ApplyJobPresenter

    override fun setUpContents(savedInstanceState: Bundle?) {
        setUpToolbar(true)
        setUpToolbarText("Apply")

        mFirebaseUser = FirebaseAuth.getInstance().currentUser!!

        tvJASeekerName.text = mFirebaseUser.displayName
        tvJASeekerEmail.text = mFirebaseUser.email
        GlideApp.with(this)
                .load(mFirebaseUser.photoUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(ivJASeekerProfile)

        radioGroupCanLowerOfferAmount.setOnCheckedChangeListener { group, checkedId ->
            var rdBtn: RadioButton = group.findViewById(checkedId)
            if (checkedId > -1) {
                canLowerOfferAmount = rdBtn.text == "Yes"
            }
        }

        mPresenter = ViewModelProviders.of(this)
                .get(ApplyJobPresenter::class.java)
        mPresenter.initPresenter(this)

        mPresenter.onUiReady(intent.getIntExtra(IE_JOB_ID, 0))
                .observe(this,
                        Observer<JobListVO> { job: JobListVO? ->
                            bindDataToView(job)
                        })


    }

    private fun bindDataToView(job: JobListVO?) {
        tvApplyJobTitle.text = job!!.shortDesc
        tvJApplyobLocation.text = job!!.location
    }

    override fun navigateSendJob() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.job_apply_and_send_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item!!.itemId == R.id.action_close) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
