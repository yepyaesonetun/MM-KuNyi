package com.prime.mm_kunyi.data.vo

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
class RelevantVO(
        val whyRelevant: String? = null,
        val seekerName: String? = null,
        val seekerId: Int? = null,
        val seekerSkill: List<SeekerSkillIVO?>? = null,
        val seekerProfilePicUrl: String? = null,
        val relevancyPercentage: Double? = null
) : BaseVO {
}