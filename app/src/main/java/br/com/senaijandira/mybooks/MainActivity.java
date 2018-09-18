package br.com.senaijandira.mybooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.utils.ConvertImage;

public class MainActivity extends AppCompatActivity {

    private LinearLayout listaLivro;
    public static ArrayList<Livro> livros = new ArrayList<Livro>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaLivro = findViewById(R.id.listalivros);

        //cria livros

        livros.add(new Livro(1,ConvertImage.toByteArray(getResources(),R.drawable.pequeno_principe),
                "O pequeno Principe",getString(R.string.PeguenoPrincipe)));
        livros.add(new Livro(1,ConvertImage.toByteArray(getResources(),R.drawable.cinquenta_tons_cinza),
                "50 Tons",getString(R.string.PeguenoPrincipe)));
        livros.add(new Livro(1,ConvertImage.toByteArray(getResources(),R.drawable.kotlin_android),
                "Kotlin",getString(R.string.PeguenoPrincipe)));
        livros.add(new Livro(1,ConvertImage.toByteArray(getResources(),R.drawable.pequeno_principe),
                "O pequeno Principe",getString(R.string.PeguenoPrincipe)));
        livros.add(new Livro(1,ConvertImage.toByteArray(getResources(),R.drawable.cinquenta_tons_cinza),
                "50 Tons",getString(R.string.PeguenoPrincipe)));
        livros.add(new Livro(1,ConvertImage.toByteArray(getResources(),R.drawable.kotlin_android),
                "Kotlin",getString(R.string.PeguenoPrincipe)));
        /*
        Livro livroTmp = new Livro();

        byte[] capa = Utils.toByteArray(getResources(), R.drawable.pequeno_principe);

        livroTmp.setId(1);
        livroTmp.setCapa(capa);
        livroTmp.setTitulo("Pequeno Principe");
        livroTmp.setDescricao("Descrição do pequeno princpe:");

        addLivro(livroTmp,listaLivro);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        listaLivro.removeAllViews();
        for(Livro l :livros){
            addLivro(l,listaLivro);
        }
    }

    private void addLivro(Livro livro, ViewGroup root){
                //carrega o layout do arquivo livro_layuot passado o aonde estará root e dizendo que o objeto não herdadra atruibutos do root
        View v = LayoutInflater.from(this).inflate(R.layout.livro_layout,root,false);

        ImageView capa = v.findViewById(R.id.imgLivroCapa);// pega o elemento dentro da v

        TextView titulo = v.findViewById(R.id.txtLivroTitulo);

        TextView descricao = v.findViewById(R.id.txtLivroDescricao);

        capa.setImageBitmap(ConvertImage.toBitmap(livro.getCapa()));

        titulo.setText(livro.getTitulo());

        descricao.setText(livro.getDescricao());

        root.addView(v);
    }
    public void abrirCadastro(View v){
        startActivity(new Intent(this,CadastroActivity.class));
    }
}
