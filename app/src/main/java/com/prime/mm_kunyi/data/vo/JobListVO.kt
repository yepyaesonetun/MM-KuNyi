package com.prime.mm_kunyi.data.vo

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.prime.mm_kunyi.persistence.typeconverters.*

/**
 * Created by yepyaesonetun on 8/2/18.
 **/
@Entity(tableName = "jobList")
@TypeConverters(ImagesTypeConverter::class, ApplicantTypeConverter::class, RelevantTypeConverter::class,
        ViewedTypeConverter::class, RequiredSkillTypeConverter::class, InterestedTypeConverter::class,
        JobTagTypeConverter::class)

class JobListVO(var images: List<String>? = null,
                @PrimaryKey
                var jobPostId: Int? = null,
                var genderForJob: Int? = null,
                @Embedded
                var jobDuration: JobDurationVO? = null,
                var isActive: Boolean? = null,
                var availablePostCount: Int? = null,
                var fullDesc: String? = null,
                var phoneNo: String? = null,
                var applicant: List<ApplicantVO>? = null,
                var postedDate: String? = null,
                var relevant: List<RelevantVO>? = null,
                var makeMoneyRating: Int? = null,
                var importantNotes: List<String>? = null,
                var viewed: List<ViewedVO>? = null,
                @Embedded
                var offerAmount: OfferAmount? = null,
                var location: String? = null,
                var requiredSkill: List<RequiredSkillVO>? = null,
                var shortDesc: String? = null,
                var interested: List<InterestedVO>? = null,
                var jobTags: List<JobTagsVO>? = null,
                var postClosedDate: String? = null,
                var email: String? = null) : BaseVO {
}