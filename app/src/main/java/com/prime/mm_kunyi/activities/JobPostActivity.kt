package com.prime.mm_kunyi.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.mvp.presenters.PostNewJobPresenter
import com.prime.mm_kunyi.mvp.views.PostNewJobView

class JobPostActivity : BaseActivity(), PostNewJobView {

    override fun getlayoutRes(): Int {
        return R.layout.activity_job_post
    }

    companion object {

        fun getNewJobPostActivityIntent(context: Context): Intent {
            val intent = Intent(context, JobPostActivity::class.java)
            return intent
        }
    }

    private lateinit var mPresenter: PostNewJobPresenter

    override fun setUpContents(savedInstanceState: Bundle?) {
        setUpToolbar(true)
        setUpToolbarText("Post New Job")

        mPresenter = ViewModelProviders.of(this)
                .get(PostNewJobPresenter::class.java)
        mPresenter.initPresenter(this)


    }


}
