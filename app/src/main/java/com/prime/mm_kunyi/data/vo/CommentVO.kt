package com.prime.mm_kunyi.data.vo

import java.util.*

/**
 * Created by yepyaesonetun on 8/10/18.
 **/
class CommentVO(var commentId: Long? = null,
                var userId: String? = null,
                var commentDate: String? = null,
                var commentUserName: String? = null,
                var commentUserPhoto: String? = null,
                var commentDetail: String? = null) {

    companion object {
        fun initComment(userId: String, username: String, photoUrl: String, details: String): CommentVO {
            var commentVO = CommentVO()
            commentVO.commentId = System.currentTimeMillis() / 100
            commentVO.userId = userId
            commentVO.commentDate = Calendar.getInstance().time.toString()
            commentVO.commentUserName = username
            commentVO.commentUserPhoto = photoUrl
            commentVO.commentDetail = details
            return commentVO
        }
    }
}