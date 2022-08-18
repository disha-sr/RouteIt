package com.raywenderlich.android.runtracker

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitInterface {
    @get:GET("posts")
    val posts: Call<List<PostModel?>?>?
    companion object{
        const val BASE_URL="https://jsonplaceholder.typicode.com"
    }
    @POST("nearestRoads?key=AIzaSyCp90THXFeHXFdGTqUarOtXMRZcxzjKfwE")
    fun getNearestRoad(@Field("points") points: String?): Call<PostModel?>?




}