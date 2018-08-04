package com.prime.mm_kunyi.data.models

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.*
import com.prime.mm_kunyi.data.vo.JobListVO

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
class JobListModel private constructor(context: Context) : BaseModel(context) {

    companion object {
        private lateinit var mDataBaseReference: DatabaseReference
        private var MM_KUNYI_FEED: String = "mm-kunyi-ec79d"
        private var mFirebaseAuth: FirebaseAuth? = null
        private var mFirebaseUser: FirebaseUser? = null
        private var INSTANCE: JobListModel? = null

        fun getInstance(): JobListModel {
            if (INSTANCE == null) {
                throw RuntimeException("JobListModel is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initializeJobListModel(context: Context) {
            INSTANCE = JobListModel(context)
        }
    }

    init {
        mDataBaseReference = FirebaseDatabase.getInstance().reference
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mFirebaseAuth!!.currentUser
    }


    fun loadJobsFeed(mJobsLD: MutableLiveData<List<JobListVO>>, mErrorLD: MutableLiveData<String>) {

        mDataBaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                mErrorLD.value = error.message
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val jobsList = ArrayList<JobListVO>()
                if (dataSnapshot != null) {

                    for (snapShot in dataSnapshot.children) {
                        val jobItem: JobListVO = snapShot.getValue<JobListVO>(JobListVO::class.java)!!
                        jobsList.add(jobItem)
                    }
                    mJobsLD.value = jobsList
                    // persist to DB
                    persistToDB(jobsList)

                } else
                    mErrorLD.value = "Cannot load data. Please Try Again"
            }

        })

    }

    private fun persistToDB(jobsList: ArrayList<JobListVO>) {
        mTheDB.jobListDao().insertAll(jobsList)
    }

    fun getJobList(): LiveData<List<JobListVO>> {
        return mTheDB.jobListDao().getJobList()
    }

    fun getJobById(jobId: Int): LiveData<JobListVO> {
        return mTheDB.jobListDao().getJobById(jobId)
    }

    fun authenticateUserWithGoogleAccount(signInAccount: GoogleSignInAccount, delegate: SignInWithGoogleAccountDelegate) {
        val credential = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
        mFirebaseAuth!!.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        delegate.onFailureSignIn(task.exception!!.message!!)
                    } else {
                        delegate.onSuccessSignIn(signInAccount)
                    }
                }
                .addOnFailureListener { e ->
                    delegate.onFailureSignIn(e.message!!)
                }
    }

    interface SignInWithGoogleAccountDelegate {
        fun onSuccessSignIn(signInAccount: GoogleSignInAccount)
        fun onFailureSignIn(msg: String)
    }

    fun isUserSignIn(): Boolean {
        return mFirebaseUser != null
    }

    fun getUserInfo(): FirebaseUser {
        return mFirebaseUser!!
    }
}