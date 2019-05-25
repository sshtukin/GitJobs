package com.sshtukin.gitjobs.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Job {

    @SerializedName("company")
    @Expose
    lateinit var company: String

    @SerializedName("title")
    @Expose
    lateinit var position: String

    @SerializedName("location")
    @Expose
    lateinit var location: String
}