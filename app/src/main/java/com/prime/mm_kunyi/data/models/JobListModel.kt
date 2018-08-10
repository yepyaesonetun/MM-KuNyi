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
import com.prime.mm_kunyi.data.vo.ApplicantVO
import com.prime.mm_kunyi.data.vo.CommentVO
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.data.vo.LikeVO

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
                        when {
                            jobItem.like == null -> jobItem.like = ArrayList()
                        }
                        when {
                            jobItem.comment == null -> jobItem.comment = ArrayList()
                        }
                        when {
                            jobItem.applicant == null -> jobItem.applicant = ArrayList()
                        }
                        when {
                            jobItem.interested == null -> jobItem.interested = ArrayList()
                        }
                        when {
                            jobItem.jobTags == null -> jobItem.jobTags = ArrayList()
                        }
                        when {
                            jobItem.viewed == null -> jobItem.viewed = ArrayList()
                        }
                        when {
                            jobItem.viewed == null -> jobItem.viewed = ArrayList()
                        }
                        when {
                            jobItem.relevant == null -> jobItem.relevant = ArrayList()
                        }
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

    fun addLike(jobId: String, likeId: String) {
        val like = LikeVO.initLike(mFirebaseUser!!.uid)
        mDataBaseReference.child(jobId).child("like")
                .child(likeId).setValue(like)
    }

    fun addComment(jobId: String, commentId:String, commentContent: String){
        mFirebaseAuth=FirebaseAuth.getInstance()
        mFirebaseUser= mFirebaseAuth!!.currentUser
        val comment = CommentVO.initComment(mFirebaseUser!!.uid, mFirebaseUser!!.displayName.toString(), mFirebaseUser!!.photoUrl.toString(), commentContent)
        mDataBaseReference.child(jobId).child("comment")
                .child(commentId).setValue(comment)
    }

    fun addNewJob(jobId: String, job: JobListVO, delegate: AddNewJobDelegate) {
        mDataBaseReference.child(jobId).setValue(job)
        delegate.onSuccessMsg("Successfully Published!")
    }

    interface AddNewJobDelegate {
        fun onSuccessMsg(successMsg: String)
    }

    fun applyJob(jobID: String, applicantId: String, seekerSkills: String) {
        val applicant: ApplicantVO = ApplicantVO
                .initApplicant(mFirebaseUser!!.displayName,
                        mFirebaseUser!!.photoUrl.toString(),
                        seekerSkills)
        mDataBaseReference.child(jobID).child("applicant")
                .child(applicantId).setValue(applicant)
    }

}