package com.uca.edu.campussensors.api

import com.uca.edu.campussensors.model.Dispositivo
import retrofit2.Call

import retrofit2.http.GET

interface ApiService {
    @GET("dashboard?fechaInicio=2024-06-19")
    fun getDispositivos(): Call<List<Dispositivo>>
    
}