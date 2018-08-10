package com.prime.mm_kunyi.data.vo

/**
 * Created by yepyaesonetun on 8/10/18.
 **/
class LikeVO(var likeId: Long? = null, var userId: String? = null, var timeStamp: Long? = null) {

    companion object {
        fun initLike(uid: String): LikeVO {
            val likeVO = LikeVO()
            likeVO.likeId = System.currentTimeMillis()/1000
            likeVO.userId = uid
            likeVO.timeStamp = System.currentTimeMillis()
            return likeVO
        }
    }
}