package com.prime.mm_kunyi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.delegates.JobItemDelegate
import com.prime.mm_kunyi.viewholders.BaseViewHolder
import com.prime.mm_kunyi.viewholders.JobListViewHolder

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
class JobListAdapter(context: Context, private val mDelegate: JobItemDelegate) : BaseRecyclerAdapter<JobListViewHolder, JobListVO>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<JobListVO> {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_job, parent, false);
        return JobListViewHolder(view, mDelegate)
    }
}