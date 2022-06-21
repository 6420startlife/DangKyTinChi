package com.ptithcm.dangkytinchi.interfaces;

import com.ptithcm.dangkytinchi.request.RequestHome;
import com.ptithcm.dangkytinchi.response.ResponseHome;
import com.ptithcm.dangkytinchi.response.ResponseMarks;
import com.ptithcm.dangkytinchi.response.ResponseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("Login/Login")
    Call<String> login(@Query("username") String username, @Query("pass") String pass);

    @GET("BangDiem/GetBangDiemByMaSV")
    Call<List<ResponseMarks>> loadMarks(@Query("maSV") String maSV);

    @GET("Student/GetById")
    Call<ResponseUser> loadUser(@Query("id") String id);

    @GET("LopTC/GetDSLopTCChuaDangKyByMaSV")
    Call<List<ResponseHome>> loadHome(@Query("Masv") String Masv);
    @GET("LopTC/GetDSLopTCDaDangKyByMaSV")
    Call<List<ResponseHome>> loadRegistered(@Query("Masv") String Masv);

    @POST("DangKy")
    Call<String> dangKy(@Body List<RequestHome> requestHomeList);

    @HTTP(method = "DELETE", path = "DangKy", hasBody = true)
    Call<String> huyDangKy(@Body List<RequestHome> requestHomeList);
}
