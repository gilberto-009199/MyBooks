package br.com.senaijandira.mybooks.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

public class Alertas {

    public static void Alerta(Context contexto, String titulo, String msg, Dialog.OnClickListener positive, Dialog.OnClickListener negative){
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle(titulo);
        alert.setMessage(msg);

        //nao pode cancelar o alert ao clicar fora da caixa
        alert.setCancelable(false);

        alert.setPositiveButton("Ok", positive);
        if(negative!=null){
            alert.setNegativeButton("Cancelar", negative);
        }
        alert.create().show();
    }

}
