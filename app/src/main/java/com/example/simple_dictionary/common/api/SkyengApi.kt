package com.example.simple_dictionary.common.api

import com.example.simple_dictionary.search.data.SearchResult
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {

    @GET("/api/public/v1/meanings")
    fun getMeaning(@Query("ids") ids: String): Single<RequestBody>

    @GET("/api/public/v1/words/search")
    fun search(@Query("search") search: String): Single<List<SearchResult>>

}