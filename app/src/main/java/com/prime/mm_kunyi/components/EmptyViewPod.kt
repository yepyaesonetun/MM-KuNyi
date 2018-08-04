package com.prime.mm_kunyi.components

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.empty_view_pod.view.*

/**
 * Created by yepyaesonetun on 8/1/18.
 **/
class EmptyViewPod : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setEmptyData(emptyImageId: Int, emptyMsg: String) {
        ivEmpty!!.setImageResource(emptyImageId)
        tvEmpty!!.text = emptyMsg
    }

    fun setEmptyData(emptyMsg: String) {
        tvEmpty!!.text = emptyMsg
    }
}