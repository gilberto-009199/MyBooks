package br.com.senaijandira.mybooks;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.utils.Utils;

import static br.com.senaijandira.mybooks.MainActivity.livros;

public class CadastroActivity extends AppCompatActivity {

    EditText txtTitulo,txtDesc;;
    ImageView imgLivroCapa;
    Bitmap livroCapa;

    private final int CODE_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        imgLivroCapa = findViewById(R.id.imgLivroCapa);
        txtDesc = findViewById(R.id.txtDesc);
        txtTitulo = findViewById(R.id.txtTitulo);
    }

    public void abrirGaleria(View view) {
        Intent intentgaleria = new Intent(Intent.ACTION_GET_CONTENT);

        intentgaleria.setType("image/*");

        startActivityForResult(intentgaleria.createChooser(intentgaleria,"Selecione uma imagem!"),CODE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODE_REQUEST && resultCode== Activity.RESULT_OK){
            //CASO ELE TENHA SELECIONADO E NÃO TENHA FECHADO
            try{
                InputStream input = getContentResolver().openInputStream(data.getData());
                livroCapa = BitmapFactory.decodeStream(input);
            }catch(Exception e){
                e.printStackTrace();
            }
            imgLivroCapa.setImageBitmap(livroCapa);
        }
    }

    public void salvarLivro(View view) {
        String titulo = txtTitulo.getText().toString();
        String desc =  txtDesc.getText().toString();

        System.out.println("Campos : titulo:"+titulo.length()+" desc: "+desc.length()+" .");

        if( titulo.length()==0 || desc.length()==0){
            System.out.println("Um dos Campos esta nullo");
            Utils.Alerta(this,"Erro!!","Por Favor!! Preencha todos os campos.",new Dialog.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    txtTitulo.setTextColor(Color.parseColor("#f44242"));
                    txtDesc.setTextColor(Color.parseColor("#f44242"));
                }
            },null);
            return;
        }

        if(livroCapa==null){
            System.out.println("Imagem Não selecionada");
            Utils.Alerta(this,"Erro!!","Por Favor!! Escolha uma Imagem de Capa.",new Dialog.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    imgLivroCapa.setImageBitmap(Utils.toBitmap(Utils.toByteArray(getResources(),R.drawable.error)));
                }
            },null);
            return;
        }else {
            byte[] capa = Utils.toByteArray(livroCapa);

            Livro livroTmp = new Livro(0, capa, titulo, desc);
            livros.add(livroTmp);


            Utils.Alerta(this, "Cadastrado!!", "o livro foui cadastrado com sucessso", new Dialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            }, null);


        /*Para Array*/
        /*
        * MainActivity.livros= Array.copyOf(MainActivity.livros,tamanhoArray+1);
        *
        */
        }

    }
}
