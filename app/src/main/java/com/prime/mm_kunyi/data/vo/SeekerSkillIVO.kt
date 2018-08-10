package com.prime.mm_kunyi.data.vo

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
class SeekerSkillIVO(
        var skillName: String? = null,
        var skillId: Int? = null
) : BaseVO {

    companion object {
        fun initSeekerSkill(skillName: String? = null, id: Int? = null): SeekerSkillIVO {
            val seekerSkillIVO = SeekerSkillIVO()
            seekerSkillIVO.skillId = id!! + 1
            seekerSkillIVO.skillName = skillName

            return seekerSkillIVO
        }
    }
}