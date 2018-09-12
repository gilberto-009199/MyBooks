package br.com.senaijandira.mybooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private LinearLayout listaLivro;
    public static Livro[] livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaLivro = findViewById(R.id.listalivros);

        //cria livros

        livros = new Livro[]{
                new Livro(1,Utils.toByteArray(getResources(),R.drawable.pequeno_principe),
                        "oi",getString(R.string.PeguenoPrincipe)),
                new Livro(1,Utils.toByteArray(getResources(),R.drawable.pequeno_principe),
                        "oi",getString(R.string.PeguenoPrincipe))
        };


        for(Livro l :livros){
            addLivro(l,listaLivro);
        }
        /*
        Livro livroTmp = new Livro();

        byte[] capa = Utils.toByteArray(getResources(), R.drawable.pequeno_principe);

        livroTmp.setId(1);
        livroTmp.setCapa(capa);
        livroTmp.setTitulo("Pequeno Principe");
        livroTmp.setDescricao("Descrição do pequeno princpe:");

        addLivro(livroTmp,listaLivro);*/
    }
    private void addLivro(Livro livro, ViewGroup root){
                //carrega o layout do arquivo livro_layuot passado o aonde estará root e dizendo que o objeto não herdadra atruibutos do root
        View v = LayoutInflater.from(this).inflate(R.layout.livro_layout,root,false);

        ImageView capa = v.findViewById(R.id.imgLivroCapa);// pega o elemento dentro da v

        TextView titulo = v.findViewById(R.id.txtLivroTitulo);

        TextView descricao = v.findViewById(R.id.txtLivroDescricao);

        capa.setImageBitmap(Utils.toBitmap(livro.getCapa()));

        titulo.setText(livro.getTitulo());

        descricao.setText(livro.getDescricao());

        root.addView(v);
    }
}
