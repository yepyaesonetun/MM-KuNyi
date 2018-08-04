package com.prime.mm_kunyi.delegates

import com.prime.mm_kunyi.mvp.presenters.MainPresenter

/**
 * Created by yepyaesonetun on 8/1/18.
 **/
interface HomePresenterDelegate {
    fun getPresenter(): MainPresenter
}