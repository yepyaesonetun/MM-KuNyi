package com.prime.mm_kunyi.data.vo

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
class ApplicantVO(var seekerName: String? = null,
                  var canLowerOfferAmount: Boolean? = null,
                  var appliedDate: String? = null,
                  var seekerId: Int? = null,
                  var seekerSkill: List<SeekerSkillIVO?>? = null,
                  var seekerProfilePicUrl: String? = null,
                  var whyShouldHire: List<WhyShouldHireVO?>? = null
) : BaseVO {

    companion object {
        fun initApplicant(name: String?,
                          profilePictureUrl: String? = null,
                          seekerSkills: String? = null): ApplicantVO {
            var applicant = ApplicantVO()
            applicant.seekerName = name
            applicant.seekerProfilePicUrl = profilePictureUrl
            applicant.seekerId = (System.currentTimeMillis() / 100).toInt()
            applicant.appliedDate = Calendar.getInstance().time.toString()
            applicant.seekerSkill = listOf(SeekerSkillIVO.initSeekerSkill(seekerSkills, 0))
            applicant.whyShouldHire = ArrayList()
            applicant.canLowerOfferAmount = false

            return applicant
        }
    }
}