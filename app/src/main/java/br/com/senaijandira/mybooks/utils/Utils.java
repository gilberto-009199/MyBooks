package br.com.senaijandira.mybooks.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Utils {

    public static final String DATABASE_NAME = "mybooks.db";

    public static byte[] toByteArray( Bitmap bitmap){

        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , blob);

        return blob.toByteArray();
    }

    public static byte[] toByteArray(Resources res,  int imgResource){
        Bitmap bitmap = BitmapFactory.decodeResource(res, imgResource);

        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , blob);

        return blob.toByteArray();
    }


    public static Bitmap toBitmap(byte[] imagem){
        return BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
    }
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
