package com.prime.mm_kunyi.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.components.EmptyViewPod
import com.prime.mm_kunyi.components.SmartRecyclerView
import com.prime.mm_kunyi.delegates.HomePresenterDelegate
import com.prime.mm_kunyi.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_my_jobs.*


/**
 * A simple [Fragment] subclass.
 */
class MyJobsFragment : Fragment() {

    private lateinit var mPresenter: MainPresenter
    private lateinit var mHomePresenterDelegate: HomePresenterDelegate

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_my_jobs, container, false)
        val rv: SmartRecyclerView = view.findViewById(R.id.rvMyJobs)
        val emptyView: EmptyViewPod = view.findViewById(R.id.vpEmpty)

        mPresenter = mHomePresenterDelegate.getPresenter()

        emptyView.setEmptyData(R.drawable.empty_my_job, "You have no recorded activity yet")
        rv.setEmptyView(emptyView)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mHomePresenterDelegate = context as HomePresenterDelegate
    }

    fun addDataToAdapter(){
        // parse data as parameter if this fun used and add its to rvAdapter
    }

}// Required empty public constructor
