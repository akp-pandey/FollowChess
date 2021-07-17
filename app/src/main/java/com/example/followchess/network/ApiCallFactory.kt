package com.example.followchess.network

import com.example.followchess.utility.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiCallFactory {
//Function to get Retrofit Instance
    fun getRetrofitInstance():ApiService{

        //Creating object of OkHttpClient
        val builder= OkHttpClient.Builder()

        //Creating object for interceptor
        val loggingInterceptor=HttpLoggingInterceptor()

        //Setting level of interceptor
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY

        //Adding interceptor to builder
        builder.addInterceptor(loggingInterceptor)

        //building the builder
        val client=builder.build()

        //Creating retrofit instance with base url ="https://followchess.com/"
        val retrofit=Retrofit.Builder().baseUrl(Constants.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()

        //returning the instance of Retrofit
        return retrofit.create(ApiService::class.java)

    }
}