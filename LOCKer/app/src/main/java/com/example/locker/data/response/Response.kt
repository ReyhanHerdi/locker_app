package com.example.locker.data.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("benefits")
	val benefits: String? = null,

	@field:SerializedName("requirements")
	val requirements: String? = null,

	@field:SerializedName("employment_type")
	val employmentType: String? = null,

	@field:SerializedName("has_questions")
	val hasQuestions: String? = null,

	@field:SerializedName("required_education")
	val requiredEducation: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("industry")
	val industry: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("has_company_logo")
	val hasCompanyLogo: String? = null,

	@field:SerializedName("salary_range")
	val salaryRange: String? = null,

	@field:SerializedName("company_profile")
	val companyProfile: String? = null,

	@field:SerializedName("function")
	val function: String? = null,

	@field:SerializedName("required_experience")
	val requiredExperience: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("department")
	val department: String? = null,

	@field:SerializedName("telecommuting")
	val telecommuting: String? = null
)
