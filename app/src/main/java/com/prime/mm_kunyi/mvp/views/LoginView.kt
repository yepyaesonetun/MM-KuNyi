package com.prime.mm_kunyi.mvp.views

/**
 * Created by yepyaesonetun on 8/4/18.
 **/
interface LoginView : BaseView {

    fun onTapGoogleSingIn()
    fun navigateToMainActivity()
    fun decideWhatToDo()
}