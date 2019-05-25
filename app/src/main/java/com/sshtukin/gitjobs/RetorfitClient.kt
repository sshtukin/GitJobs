package com.sshtukin.gitjobs

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class-singleton to build Retrofit Client
 *
 * @author Sergey Shtukin
 */

object RetrofitClient {
    private var retrofit: Retrofit? = null

    internal fun getClient(url: String): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }
}