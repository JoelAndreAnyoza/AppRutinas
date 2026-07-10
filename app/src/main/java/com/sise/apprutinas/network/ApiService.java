package com.sise.apprutinas.network;

import com.sise.apprutinas.model.Frase;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("phrase/esp")
    Call<Frase> obtenerFrase(@Query("category_id") int categoria);

}