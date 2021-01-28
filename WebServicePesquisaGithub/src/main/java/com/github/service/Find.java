package com.github.service;

import com.github.github.models.FindRepository;
import com.github.github.models.FindUserData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Find {

    private static String URL_BASE = "https://api.github.com/";
    private static Integer MAX_REQUESTS_SESSIONS = 0;//Qundo este número atingir 1000 requisições a api nao fará mais requisições ao Github

    public static FindUserData FindUserData(String userName) throws IOException {

        if(MAX_REQUESTS_SESSIONS >= 1000){
            System.err.println("\nO número Máximo de conexões pelo programa foi atingido!\n");
            return null;
        }

        URL urlFindUser = new URL(URL_BASE + "users/" + userName);
        HttpURLConnection conectionFinUser = (HttpURLConnection) urlFindUser.openConnection();
        MAX_REQUESTS_SESSIONS++;

        URL urlFindFollowersRepositoryes = new URL(URL_BASE + "users/" + userName +"/followers");
        HttpURLConnection conectionFindFollowersRepositoryes = (HttpURLConnection) urlFindFollowersRepositoryes.openConnection();
        MAX_REQUESTS_SESSIONS++;

        BufferedReader responseFindUser = new BufferedReader(new InputStreamReader((conectionFinUser.getInputStream())));
        BufferedReader responseFinFollowersRepositoryes = new BufferedReader(new InputStreamReader((conectionFindFollowersRepositoryes.getInputStream())));

        String rfu = convertJsonToString(responseFindUser);
        String rffr = convertJsonToString(responseFinFollowersRepositoryes);

        FindUserData findUserData = new Gson().fromJson(rfu, FindUserData.class);
        findUserData.setFollowersNames(new Gson().fromJson(rffr, new TypeToken<List<FindUserData>>(){}.getType()));

        for (int pos =0; pos < findUserData.getFollowersNames().size(); pos++) {

            URL url = new URL(URL_BASE + "users/" + findUserData.getFollowersNames().get(pos).getLogin() + "/repos");
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            MAX_REQUESTS_SESSIONS++;

            BufferedReader response = new BufferedReader(new InputStreamReader((conection.getInputStream())));
            String responseJson = convertJsonToString(response);

            List<FindRepository> find = new Gson().fromJson(responseJson, new TypeToken<List<FindRepository>>(){}.getType());

            findUserData.getFollowersRepositoryes().add(find);
        }

        return findUserData;
    }//FindRepository

    public static String convertJsonToString(BufferedReader buffereReader) throws IOException {
        String response, jsonValue = "";
        while ((response = buffereReader.readLine()) != null) {
            jsonValue += response;
        }
        return jsonValue;
    }//converteJsonEmString

}//class Find
