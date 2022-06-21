package com.ptithcm.dangkytinchi.interfaces;

import com.ptithcm.dangkytinchi.request.RequestHome;
import com.ptithcm.dangkytinchi.response.ResponseHome;
import com.ptithcm.dangkytinchi.response.ResponseMarks;
import com.ptithcm.dangkytinchi.response.ResponseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
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
    @GET("LopTC/GetDSLopTCChuaDangKyByMaSV")
    Call<List<ResponseHome>> loadRegistered(@Query("Masv") String Masv);

    @POST("DangKy")
    Call<RequestHome> dangKy(@Body RequestHome requestHome);
    @DELETE("DangKy")
    Call<RequestHome> huyDangKy(@Body RequestHome requestHome);
}
