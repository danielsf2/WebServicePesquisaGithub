package com.github.github.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class FindRepository {

    private String id;
    private String name;
    private String full_name;
    private owner owner;

    public static String printData(List<List<FindRepository>> finds){

        StringBuffer sb = new StringBuffer();

        for (var temp: finds) {
            sb.append(temp.get(0).owner.login + " : ");
            sb.append("\n");
            for (var aux: temp)
                sb.append("\t" + aux.getName() + "\n");
            sb.append("\n");
        }

        return sb.toString();
    }//printData

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public class owner{
        private String login;
    }

}//class FindRepository
