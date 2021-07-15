package com.zidney.jidquiz.retrofit;

import com.zidney.jidquiz.model.ResultsModel;
import com.zidney.jidquiz.model.Tokens;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {

    //category = General Knowlegde(9), Film(11), Science&Nature(17), Mythology(20)
    //type = multiple
    //difficulty = easy, medium, hard
    //amount = amount of question

    @GET("api_token.php?command=request")
    Call<Tokens> getTokenTrivia();

    @GET("api.php?")
    Call<ResultsModel> getTriviaQuiz(@Query("amount")int amount, @Query("category")int category, @Query("difficulty")String difficulty, @Query("type")String type, @Query("token")String token);


}
