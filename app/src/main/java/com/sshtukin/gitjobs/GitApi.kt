package com.sshtukin.gitjobs

import com.sshtukin.gitjobs.model.Job
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    @GET("/positions.json")
    fun getJobResults(
        @Query("description") position: String,
        @Query("location") location: String
    ): Call<List<Job>>
}