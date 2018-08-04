package com.prime.mm_kunyi.data.vo

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
class ApplicantVO(seekerName: String? = null,
                     val canLowerOfferAmount: Boolean? = null,
                     val appliedDate: String? = null,
                     val seekerId: Int? = null,
                     val seekerSkill: List<SeekerSkillIVO?>? = null,
                     val seekerProfilePicUrl: String? = null,
                     val whyShouldHire: List<WhyShouldHireVO?>? = null
) : BaseVO {
}