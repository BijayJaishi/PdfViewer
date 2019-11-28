package com.bijay.pdfconverter;

import com.bijay.pdfconverter.Model.Data;
import com.bijay.pdfconverter.Model.Model_Class;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface RetrofitInterface {
    @Headers({"x-api-key:S@ve@Res!"})
    @GET("filePdf")
    Call<Model_Class> getMessage();
}
