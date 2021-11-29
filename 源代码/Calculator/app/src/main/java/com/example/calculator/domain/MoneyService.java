package com.example.calculator.domain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoneyService {
    @GET("/fxrate/index")
    Call<MoneyInfomation> getMoneyData(@Query("key") String key, @Query("fromcoin") String fromcoin,
                                       @Query("tocoin") String tocoin, @Query("money") String money);
}
