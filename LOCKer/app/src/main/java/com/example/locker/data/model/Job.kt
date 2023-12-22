package com.example.locker.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Job(
    var title: String,
    var department: String,
    var companyTitle: String,
    var requirement: String,
    var telecomuting: String,
    var has_questions: String,
    var required_experience: String,
    var industry: String,
    var location: String,
    var salaryRange: String,
    var description: String,
    var benefits: String,
    var has_company_logo: String,
    var employmrnt_type: String,
    var requiredEducation: String,
) : Parcelable
