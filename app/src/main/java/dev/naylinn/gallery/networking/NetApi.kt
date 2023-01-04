package dev.naylinn.gallery.networking

import dev.naylinn.gallery.networking.model.CategoryResponse
import dev.naylinn.gallery.networking.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.*

interface NetApi {
    @FormUrlEncoded
    @POST("/api/v1/get-category")
    suspend fun getCategory(
        @Field("app_secret") appSecret: String,
        @Field("page") page: Int,

        ): Response<CategoryResponse>

    @FormUrlEncoded
    @POST("/api/v1/get-photos")
    suspend fun getPhotos(
        @Field("app_secret") appSecret: String,
        @Field("page") page: Int,
    ): Response<PhotoResponse>

    @FormUrlEncoded
    @POST("/api/v1/get-photos-by-category")
    suspend fun getPhotosByCategory(
        @Field("app_secret") appSecret: String,
        @Field("category_id") categoryId: Int,
        @Field("page") page: Int,
    ): Response<PhotoResponse>

}