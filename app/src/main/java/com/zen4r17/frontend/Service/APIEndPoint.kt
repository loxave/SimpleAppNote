package com.zen4r17.frontend.Service


import com.zen4r17.frontend.Model.NoteModel
import com.zen4r17.frontend.Model.SubmitModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIEndPoint {

    @GET("data.php")

    fun data(): retrofit2.Call<NoteModel>

    @FormUrlEncoded
    @POST("create.php")

    fun create(
        @Field("note") note: String
    ): retrofit2.Call<SubmitModel>

    @FormUrlEncoded
    @POST("update.php")

    fun update(
        @Field("id") id: String,
        @Field("note") note: String
    ): retrofit2.Call<SubmitModel>

    @FormUrlEncoded
    @POST("delete.php")

    fun delete(
        @Field("id") id: String,
        //@Field("note") note: String
    ): retrofit2.Call<SubmitModel>


}