package com.prime.mm_kunyi.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.data.models.JobListModel
import com.prime.mm_kunyi.mvp.presenters.LogInPresenter
import com.prime.mm_kunyi.mvp.views.LoginView
import com.prime.mm_kunyi.utils.AppConstants.Companion.RC_GOOGLE_SIGN_IN
import com.prime.mm_kunyi.utils.GlideApp
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity(), LoginView, GoogleApiClient.OnConnectionFailedListener {

    private var mGoogleApiClient: GoogleApiClient? = null
    private var mFirebaseUser: FirebaseUser? = null
    private lateinit var mPresenter: LogInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        GlideApp.with(this)
//                .load("https://images.pexels.com/photos/963486/pexels-photo-963486.jpeg?cs=srgb&dl=analog-art-beautiful-963486.jpg&fm=jpg")
                .load("https://images.pexels.com/photos/301703/pexels-photo-301703.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=350")
                .centerCrop()
                .into(ivLogIn)

        GlideApp.with(this)
                .load(R.drawable.empty_my_job)
                .transforms(CenterCrop(), RoundedCorners(8))
                .into(ivAppIcon)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("101696118419-kpqipuscs4m8fa7jg3qr9eqm3irtol8m.apps.googleusercontent.com")
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(applicationContext)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build()

        mFirebaseUser = FirebaseAuth.getInstance().currentUser
        mPresenter = ViewModelProviders.of(this).get(LogInPresenter::class.java)
        mPresenter.initPresenter(this)


        decideWhatToDo()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Snackbar.make(btnGoogleSingIn, "Connection failed.\nPlease Turn on WiFi or Mobile Data", Snackbar.LENGTH_LONG).show()
    }

    override fun navigateToMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    /**
     * decide the State need to show Home Activity or maintain the Current Login Activity
     */
    override fun decideWhatToDo() {
        if (mFirebaseUser != null) {
            navigateToMainActivity()
            finish()
        } else {
            btnGoogleSingIn.setOnClickListener { mPresenter.onTapGoogleSignIn() }
        }
    }

    override fun onTapGoogleSingIn() {
        signInWithGoogle()
    }

    private fun signInWithGoogle() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            processGoogleSignInResult(result)
        }
    }

    private fun processGoogleSignInResult(signInResult: GoogleSignInResult) {
        if (signInResult.isSuccess) {
            val account = signInResult.signInAccount
            JobListModel.getInstance().authenticateUserWithGoogleAccount(account!!, object : JobListModel.SignInWithGoogleAccountDelegate {
                override fun onSuccessSignIn(signInAccount: GoogleSignInAccount) {
                    navigateToMainActivity()
                    finish()
                }

                override fun onFailureSignIn(msg: String) {
                    Snackbar.make(btnGoogleSingIn, "Your Google sign-in failed.", Snackbar.LENGTH_LONG).show()
                }
            })
        } else {
            Snackbar.make(btnGoogleSingIn, "Your Google sign-in failed.", Snackbar.LENGTH_LONG).show()
        }
    }

}
