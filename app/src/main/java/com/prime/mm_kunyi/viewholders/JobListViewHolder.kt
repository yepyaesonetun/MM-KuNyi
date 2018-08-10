package com.prime.mm_kunyi.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.delegates.JobItemDelegate
import com.prime.mm_kunyi.utils.GlideApp
import kotlinx.android.synthetic.main.item_view_job.view.*
import java.util.*


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

        itemView.tvTotalReactions.text = "${data.like!!.size} like " + "${data.comment!!.size} comment"

        itemView.tvJobTitle.text = data.shortDesc
        itemView.tvJobLocation.text = data.location
        itemView.tvOfferAmount.text = "${data.offerAmount!!.amount.toString()} MMK"
        itemView.tvWorkingDaysPerWeek.text = data.jobDuration!!.workingDaysPerWeek.toString() + " days . per week"
        itemView.tvContactPhoneNumber.text = data.phoneNo
        itemView.tvTotalWorkingDay.text = "Total Working Day: " + data.jobDuration!!.totalWorkingDays.toString() + " Days"
        itemView.tvJobStartDate.text = "Starting Date: " + data.jobDuration!!.jobStartDate
        itemView.tvPostedDate.text = data.postedDate


        itemView.fl_feed_love.setOnClickListener(this)
        itemView.fl_feed_comment.setOnClickListener(this)
        itemView.cvJob.setOnClickListener(this)

        itemView.fl_feed_comment.setOnClickListener {
            itemView.llAddComment.visibility = View.VISIBLE
        }

        itemView.ivAddComment.setOnClickListener {
            delegate.onTapComment(data, data.comment!!.size, itemView.edtAddComment.text.toString())
            itemView.edtAddComment.text.clear()
            Toast.makeText(itemView.context, "Successfully Added", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fl_feed_love -> delegate.onTapLike(jobListVO, jobListVO.like!!.size)
            R.id.cvJob -> delegate.onTapJobItem(jobListVO.jobPostId!!)
        }
    }


}