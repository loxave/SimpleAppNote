package com.zen4r17.frontend.Service


import com.zen4r17.frontend.Model.NoteModel
import com.zen4r17.frontend.Model.SubmitModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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