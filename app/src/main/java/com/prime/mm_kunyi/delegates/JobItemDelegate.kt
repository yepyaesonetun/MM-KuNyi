package com.prime.mm_kunyi.delegates

import com.prime.mm_kunyi.data.vo.JobListVO

/**
 * Created by yepyaesonetun on 8/3/18.
 **/
interface JobItemDelegate {
    fun onTapJobItem(id: Int)
    fun onTapLike(job: JobListVO, likeSequenceID: Int)
    fun onTapComment(job: JobListVO,commentSequenceID: Int, commentContent: String)
}