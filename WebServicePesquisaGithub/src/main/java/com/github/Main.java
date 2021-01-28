package com.github;

import com.github.github.models.FindUserData;
import com.github.service.Find;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    new Main().iniciar();
    }

    private void iniciar(){
        String pesquisa = "";
        try {
            do{
                pesquisa = JOptionPane.showInputDialog (null,"\nInforme o usuário ou Digite sair para Encerrar : \n\n" ,"Pesquisa no Github", JOptionPane.INFORMATION_MESSAGE);

                if(pesquisa != "sair" || pesquisa != null){
                    FindUserData findUserData = Find.FindUserData(pesquisa);
                    JOptionPane.showMessageDialog(null, findUserData.printUserData());
                }

            }
            while(pesquisa != "sair" || pesquisa != null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
