package com.example.publicwifiinfo2022.apitest;

import com.example.publicwifiinfo2022.apidto.BaseDto;
import com.example.publicwifiinfo2022.apidto.MainDto;
import com.google.gson.Gson;


public class ApiParser {

    Gson gson = new Gson();

    public MainDto parse(String json){

        BaseDto baseDto = gson.fromJson(json, BaseDto.class);

        return baseDto.getTbPublicWifiInfo();
    }
}
