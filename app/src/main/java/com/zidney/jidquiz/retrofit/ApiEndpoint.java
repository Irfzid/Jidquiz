package com.zidney.jidquiz.retrofit;

import com.zidney.jidquiz.model.ResultsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {

    //category = General Knowlegde(9), Film(11), Science&Nature(17), Mythology(20)
    //type = multiple
    //difficulty = easy, medium, hard
    //amount = amount of question

    @GET("api.php?")
    Call<ResultsModel> getTriviaQuiz(@Query("amount")int amount,
                                           @Query("category")int category,
                                           @Query("difficulty")String difficulty,
                                           @Query("type")String type);

//    @GET("10&category=9&difficulty=medium&type=multiple")
//    Call<results> getMediumGK();
//
//    @GET("10&category=9&difficulty=hard&type=multiple")
//    Call<results> getHardGK();
//
//    @GET("10&category=11&difficulty=easy&type=multiple")
//    Call<results> getEasyFilm();
//
//    @GET("10&category=11&difficulty=medium&type=multiple")
//    Call<results> getMediumFilm();
//
//    @GET("10&category=11&difficulty=hard&type=multiple")
//    Call<results> getHardFilm();
//
//    @GET("10&category=17&difficulty=easy&type=multiple")
//    Call<results> getEasySN();
//
//    @GET("10&category=17&difficulty=medium&type=multiple")
//    Call<results> getMediumSN();
//
//    @GET("10&category=17&difficulty=hard&type=multiple")
//    Call<results> getHardSN();
//
//    @GET("10&category=20&difficulty=easy&type=multiple")
//    Call<results> getEasyMyth();
//
//    @GET("10&category=20&difficulty=medium&type=multiple")
//    Call<results> getMediumMyth();
//
//    @GET("10&category=20&difficulty=hard&type=multiple")
//    Call<results> getHardMyth();


}
