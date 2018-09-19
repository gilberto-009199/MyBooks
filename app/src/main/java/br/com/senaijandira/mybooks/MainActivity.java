package br.com.senaijandira.mybooks;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.utils.ConvertImage;

public class MainActivity extends AppCompatActivity {
    // Variavel de acesso ao banco de dados
    private MyBooksDatabase myBooksDb;
    private LinearLayout listaLivro;
    /*public static ArrayList<Livro> livros = new ArrayList<Livro>();*/
    public static Livro[] livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(new Date().toString()+": Evento onCreate iniciado ############################################");
        setContentView(R.layout.activity_main);


        /*
        *   Criando uma intancia do banco de dados usando o nome na costante da clase ConvertImage
        *
        */
        myBooksDb = Room.databaseBuilder(getApplicationContext(),MyBooksDatabase.class, ConvertImage.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        listaLivro = findViewById(R.id.listalivros);

        //cria livros

        /*livros.add(new Livro(1,ConvertImage.toByteArray(getResources(),R.drawable.pequeno_principe),
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
                */
        /*
        Livro livroTmp = new Livro();

        byte[] capa = Utils.toByteArray(getResources(), R.drawable.pequeno_principe);

        livroTmp.setId(1);
        livroTmp.setCapa(capa);
        livroTmp.setTitulo("Pequeno Principe");
        livroTmp.setDescricao("Descrição do pequeno princpe:");

        addLivro(livroTmp,listaLivro);*/
        System.out.println(new Date().toString()+": Evento onCreate finalizado");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(new Date().toString()+":Evento onStart iniciado");
        System.out.println(new Date().toString()+":Evento onStart finalizado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(new Date().toString()+":Evento onResume iniciado");
        livros = myBooksDb.daoLivro().selecionarLivros();
        listaLivro.removeAllViews();
        for(Livro l :livros){
            addLivro(l,listaLivro);
        }
        System.out.println(new Date().toString()+":Evento onResume finalizado");
    }
    private void removeLivro(Livro livro, View v){
        myBooksDb.daoLivro().deletar(livro);
        //remove o item do elemento layout listalivro
        listaLivro.removeView(v);
    }
                        // final para que a função interna do onclick linester do botão delete possa pegar o livro
    private void addLivro(final Livro livro, ViewGroup root){
                //carrega o layout do arquivo livro_layuot passado o aonde estará root e dizendo que o objeto não herdadra atruibutos do root
        final View v = LayoutInflater.from(this).inflate(R.layout.livro_layout,root,false);

        ImageView capa = v.findViewById(R.id.imgLivroCapa);// pega o elemento dentro da v

        TextView titulo = v.findViewById(R.id.txtLivroTitulo);

        TextView descricao = v.findViewById(R.id.txtLivroDescricao);

        ImageView iconeDelete = v.findViewById(R.id.imgDeleteIcon);

        iconeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeLivro(livro,v);

            }
        });

        capa.setImageBitmap(ConvertImage.toBitmap(livro.getCapa()));

        titulo.setText(livro.getTitulo());

        descricao.setText(livro.getDescricao());

        root.addView(v);
    }
    public void abrirCadastro(View v){
        startActivity(new Intent(this,CadastroActivity.class));
    }
}
