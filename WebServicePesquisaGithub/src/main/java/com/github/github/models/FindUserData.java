package com.github.github.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder

@Data
public class FindUserData {

    private String login;
    private String name;
    private String followers;
    private List<FindUserData> followersNames;
    private List<List<FindRepository>> followersRepositoryes;

    public FindUserData(){
        login = "";
        name = "";
        followers = "";
        followersNames = new ArrayList<>();
        followersRepositoryes = new ArrayList<>();
    }

    public String printUserData(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nUsuário da Consulta : " + login + "\n");
        sb.append("\n" + followers +" seguidor(es) : \n\n");
        sb.append(FindRepository.printData(followersRepositoryes));

        return sb.toString();
    }

}//class FindUser
