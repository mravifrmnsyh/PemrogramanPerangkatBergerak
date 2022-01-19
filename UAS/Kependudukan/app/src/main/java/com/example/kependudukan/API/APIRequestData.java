package com.example.kependudukan.API;

import com.example.kependudukan.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET ("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            @Field("nik") int nik,
            @Field("nama") String nama,
            @Field("ttl") String ttl,
            @Field("alamat") String alamat,
            @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("nik") int nik
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("nik") int nik
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("nik") int nik,
            @Field("nama") String nama,
            @Field("ttl") String ttl,
            @Field("alamat") String alamat,
            @Field("gender") String gender
    );
}
