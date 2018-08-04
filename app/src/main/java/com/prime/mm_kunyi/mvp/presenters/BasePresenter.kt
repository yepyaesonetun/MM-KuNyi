package com.prime.mm_kunyi.mvp.presenters

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.prime.mm_kunyi.mvp.views.BaseView

/**
 * Created by yepyaesonetun on 7/20/18.
 **/
abstract class BasePresenter<T : BaseView> : ViewModel() {

    protected lateinit var mView: T
    protected lateinit var mErrorLD: MutableLiveData<String>

    val errorLD: LiveData<String>
        get() = mErrorLD

    open fun initPresenter(mView: T) {
        this.mView = mView
        mErrorLD = MutableLiveData()
    }
}