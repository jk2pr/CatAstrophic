package com.jk.catastrophic.service

import com.jk.catastrophic.data.Cat
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ICatApi {

    @GET("/v1/images/search")
    fun getCatsAsync(
        @Query("limit") limit: String,
        @Query("page") page: Int,
        @Query("mime_types") mime_types: String
    ):
           Deferred<Response<List<Cat>>>
           // Response<List<Cat>>
}