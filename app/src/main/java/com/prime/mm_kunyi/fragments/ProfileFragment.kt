package com.prime.mm_kunyi.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.delegates.HomePresenterDelegate
import com.prime.mm_kunyi.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_profile.*


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private lateinit var mFirebaseUser: FirebaseUser
    private lateinit var mPresenter: MainPresenter
    private lateinit var mHomePresenterDelegate: HomePresenterDelegate


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        mFirebaseUser = FirebaseAuth.getInstance().currentUser!!

        val tvUserName: TextView = view.findViewById(R.id.tvUserName)
        val tvSignInEmail: TextView = view.findViewById(R.id.tvSignInEmail)
        val logOut:TextView = view.findViewById(R.id.tvLogOut)

        tvUserName.text =  mFirebaseUser.displayName
        tvSignInEmail.text = mFirebaseUser.email

        mPresenter = mHomePresenterDelegate.getPresenter()

        logOut.setOnClickListener { mPresenter.onTapLogOut() }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mHomePresenterDelegate = context as HomePresenterDelegate
    }

}// Required empty public constructor
