package com.example.publicwifiinfo2022.apitest;

import com.example.publicwifiinfo2022.apidto.MainDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiJsonServer {

    public MainDto getwifiJson(int start, int end) throws IOException {

        String url = "http://openapi.seoul.go.kr:8088/744e53725570726f38346946446961/json/TbPublicWifiInfo" +
                "/" + start + "/" + end;
        OkHttpClient okHttpClient = new OkHttpClient();

        URL urlRequest = new URL(url);
        Request request = new Request.Builder()
                .url(urlRequest)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String json = response.body().string();

        ApiParser apiParser = new ApiParser();

        return apiParser.parse(json);

    }


}
