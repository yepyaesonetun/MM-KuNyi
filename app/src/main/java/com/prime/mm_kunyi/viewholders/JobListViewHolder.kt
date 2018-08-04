package com.prime.mm_kunyi.viewholders

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.delegates.JobItemDelegate
import com.prime.mm_kunyi.utils.GlideApp
import kotlinx.android.synthetic.main.item_view_job.view.*
import java.util.*
import com.prime.mm_kunyi.R
import android.R.attr.radius
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop




/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class JobListViewHolder(view: View, private val delegate: JobItemDelegate) : BaseViewHolder<JobListVO>(view) {
    private lateinit var jobListVO: JobListVO
    private val dummyImgList = ArrayList<String>()

    init {
        dummyImgList.add("https://images.pexels.com/photos/796602/pexels-photo-796602.jpeg?cs=srgb&dl=ballpen-business-cell-phone-796602.jpg&fm=jpg")
        dummyImgList.add("https://images.pexels.com/photos/356043/pexels-photo-356043.jpeg?cs=srgb&dl=black-and-white-blackboard-business-356043.jpg&fm=jpg")
        dummyImgList.add("https://i01.appmifile.com/webfile/globalimg/2018/02141/overall-self-img.png")
        dummyImgList.add("https://i01.appmifile.com/webfile/globalimg/2018/02141/overall-cpu-bg.png")
    }

    @SuppressLint("SetTextI18n")
    override fun setData(data: JobListVO) {
        jobListVO = data

        val randomStr = Random().nextInt(dummyImgList.size)

        GlideApp.with(itemView.ivJob.context)
                .load(dummyImgList[randomStr])
                .transforms(CenterCrop(), RoundedCorners(8))
                .placeholder(R.drawable.empty_my_job)
                .error(R.drawable.empty_my_job)
                .into(itemView.ivJob)

        itemView.tvJobTitle.text = data.shortDesc
        itemView.tvJobLocation.text = data.location
        itemView.tvOfferAmount.text = data.offerAmount!!.amount.toString() + " MMK"
        itemView.tvWorkingDaysPerWeek.text = data.jobDuration!!.workingDaysPerWeek.toString() + " days . per week"


    }

    override fun onClick(v: View?) {
        delegate.onTapJobItem(jobListVO.jobPostId!!)
    }
}