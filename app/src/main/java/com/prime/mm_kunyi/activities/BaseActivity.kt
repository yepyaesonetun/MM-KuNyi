package com.prime.mm_kunyi.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by yepyaesonetun on 7/18/18.
 **/
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getlayoutRes())
        setUpContents(savedInstanceState)
    }

    abstract fun getlayoutRes(): Int

    abstract fun setUpContents(savedInstanceState: Bundle?)

    protected fun setUpToolbar(isChild: Boolean) {
        if (toolbar != null)
            setSupportActionBar(toolbar)

        if (isChild) {
            if (supportActionBar != null) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    protected fun setUpToolbarText(textValue: String) {
        toolbar_text.text = textValue
    }

    protected fun setUpToolbarIcon(icon: Int) {
        toolbar.setNavigationIcon(icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}