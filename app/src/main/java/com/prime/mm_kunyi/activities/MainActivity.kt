package com.prime.mm_kunyi.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.appinvite.AppInviteInvitation
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.prime.mm_kunyi.R
import com.prime.mm_kunyi.R.id.navigation_home
import com.prime.mm_kunyi.adapters.MyFragmentPagerAdapter
import com.prime.mm_kunyi.data.vo.JobListVO
import com.prime.mm_kunyi.delegates.HomePresenterDelegate
import com.prime.mm_kunyi.fragments.HomeFragment
import com.prime.mm_kunyi.fragments.MyJobsFragment
import com.prime.mm_kunyi.fragments.ProfileFragment
import com.prime.mm_kunyi.mvp.presenters.MainPresenter
import com.prime.mm_kunyi.mvp.views.MainView
import com.prime.mm_kunyi.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.prime.mm_kunyi.data.models.JobListModel
import kotlinx.android.synthetic.main.item_view_job.*


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MainActivity : BaseActivity(), MainView, HomePresenterDelegate, GoogleApiClient.OnConnectionFailedListener {

    override fun getlayoutRes(): Int {
        return R.layout.activity_main
    }

    companion object {
        private const val RC_APP_INVITATION: Int = 99
    }

    private var mainFragmentPagerAdapter: MyFragmentPagerAdapter? = null
    private var doubleBackToExitPressedOnce = false
    private lateinit var mPresenter: MainPresenter

    private lateinit var mFirebaseUser: FirebaseUser
    private var mGoogleApiClient: GoogleApiClient? = null
    private var latestJobID: Int? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            navigation_home -> {
                view_pager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                view_pager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                view_pager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun setUpContents(savedInstanceState: Bundle?) {
        setUpToolbar(false)
        initViews()
        mFirebaseUser = FirebaseAuth.getInstance().currentUser!!

        mPresenter = ViewModelProviders.of(this).get(MainPresenter::class.java)
        mPresenter.initPresenter(this)

        mPresenter.onUiReady()


        fab.setOnClickListener { mPresenter.onTapFabNewJobPost() }
        observeJobLists()

    }

    private fun observeJobLists() {
        mPresenter.getJobsLD().observe(this,
                Observer<List<JobListVO>> { jobList: List<JobListVO>? ->
                    displayJobList(jobList)
                    latestJobID = jobList!!.size
                })
    }

    private fun displayJobList(jobList: List<JobListVO>?) {
        val homeFragment: HomeFragment = mainFragmentPagerAdapter!!.getItem(0) as HomeFragment
        homeFragment.addDataToAdapter(jobList)
    }

    override fun getPresenter(): MainPresenter {
        return mPresenter
    }

    private fun initViews() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.removeShiftMode(navigation)

        mainFragmentPagerAdapter = MyFragmentPagerAdapter(supportFragmentManager)
        mainFragmentPagerAdapter!!.addFragment(HomeFragment(), getString(R.string.title_home))
        mainFragmentPagerAdapter!!.addFragment(MyJobsFragment(), getString(R.string.title_my_jobs))
        mainFragmentPagerAdapter!!.addFragment(ProfileFragment(), getString(R.string.title_profile))

        view_pager.offscreenPageLimit = 3
        view_pager.adapter = mainFragmentPagerAdapter
        //        view_pager.setPageTransformer(true, new ZoomOutSlideTransformer());

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        navigation.menu.getItem(0).isChecked = true
                        setUpToolbarText(getString(R.string.title_latest_jobs))
                    }
                    1 -> {
                        navigation.menu.getItem(1).isChecked = true
                        setUpToolbarText(getString(R.string.title_my_jobs))
                    }
                    2 -> {
                        navigation.menu.getItem(2).isChecked = true
                        setUpToolbarText(getString(R.string.title_me))
                    }
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("101696118419-kpqipuscs4m8fa7jg3qr9eqm3irtol8m.apps.googleusercontent.com")
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(applicationContext)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build()
    }

    override fun onBackPressed() {
        val selectedItemId = navigation.selectedItemId
        when {
            navigation_home !== selectedItemId -> setHomeItem()
            else -> {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    return
                }
                this.doubleBackToExitPressedOnce = true
                Toast.makeText(this, getString(R.string.txt_double_press_exit), Toast.LENGTH_SHORT).show()

                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_app_invitation) {
            sendInvitation()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sendInvitation() {
        val intent = AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_msg))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build()
        startActivityForResult(intent, RC_APP_INVITATION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_APP_INVITATION) {
            if (resultCode == RESULT_OK) {
                val ids = AppInviteInvitation.getInvitationIds(resultCode, data!!)
                Snackbar.make(fab, "Invitations sent to " + ids.size + " friends", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(fab, "Failed to send invitation.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setHomeItem() {
        navigation.selectedItemId = navigation_home
    }

    override fun navigateToJobDetail(jobId: Int) {
        startActivity(JobDetailActivity.getJobDetailActivityIntent(this, jobId))
    }

    override fun newJostPostProcess() {
        if (mFirebaseUser != null) {
            navigateToJobPost()
        } else {
            // Not signed in, launch the Sign In activity
            Snackbar.make(view_pager, "You need to sign with Google to crete New Job Post.", Snackbar.LENGTH_INDEFINITE).setAction("Sign-In") { signInWithGoogle() }.show()
        }
    }

    override fun navigateToJobPost() {
        startActivity(NewJobPostActivity.getNewJobPostActivityIntent(this, latestJobID!! + 1))
    }

    override fun likeProcess(job: JobListVO, likeSequenceID: Int) {
        val jobID = job.jobPostId!!.minus(1)
        JobListModel.getInstance().addLike(jobID.toString(),(likeSequenceID).toString())
    }

    override fun commentProcess(job: JobListVO,commentId: Int, commentContent:String) {
        val jobID = job.jobPostId!!.minus(1)
        JobListModel.getInstance().addComment(jobID.toString(),commentId.toString(), commentContent)
    }

    private fun signInWithGoogle() {
        val intent = Intent(container.context, LogInActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigateToLogOut() {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
