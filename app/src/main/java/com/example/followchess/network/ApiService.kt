package com.example.followchess.network

import com.example.followchess.models.Trn
import com.example.followchess.models.Trophy
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    //Calling the api using RxJava and Retrofit
    @GET("/config.json")
    fun getConfigJsonDetail():Observable<Trophy>
}