package com.prime.mm_kunyi.mvp.presenters

import com.prime.mm_kunyi.mvp.views.LoginView

/**
 * Created by yepyaesonetun on 8/4/18.
 **/
class LogInPresenter: BasePresenter<LoginView> (){
    override fun initPresenter(mView: LoginView) {
        super.initPresenter(mView)
    }

    fun onTapGoogleSignIn(){
        mView.onTapGoogleSingIn()
    }

    fun onTapSignUpWithEmail(){

    }
}