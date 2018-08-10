package com.prime.mm_kunyi.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.adapters.JobListAdapter
import com.prime.mm_kunyi.components.EmptyViewPod
import com.prime.mm_kunyi.components.SmartRecyclerView
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.delegates.HomePresenterDelegate
import com.prime.mm_kunyi.mvp.presenters.MainPresenter
import com.yammobots.mm_healthcare.components.SimpleDividerItemDecoration


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var mPresenter: MainPresenter
    private lateinit var mHomePresenterDelegate: HomePresenterDelegate
    private lateinit var mAdapter: JobListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val rv: SmartRecyclerView = view.findViewById(R.id.rvHome)
        val emptyView: EmptyViewPod = view.findViewById(R.id.vpEmpty)
        val dividerItemDecoration = SimpleDividerItemDecoration(activity!!.applicationContext)

        mPresenter = mHomePresenterDelegate.getPresenter()
        mAdapter = JobListAdapter(container!!.context, mPresenter)

        emptyView.setEmptyData(R.drawable.empty_my_job, "No Data to Display")

        rv.setEmptyView(emptyView)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
//        rv.addItemDecoration(dividerItemDecoration)
        rv.adapter = mAdapter

        return view

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mHomePresenterDelegate = context as HomePresenterDelegate
    }

    fun addDataToAdapter(jobList: List<JobListVO>?) {
        mAdapter.setNewData((jobList as MutableList<JobListVO>?)!!)
    }

}// Required empty public constructor
