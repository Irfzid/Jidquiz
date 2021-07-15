package com.zidney.jidquiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tokens {

    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("token")
    @Expose
    private String token;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
