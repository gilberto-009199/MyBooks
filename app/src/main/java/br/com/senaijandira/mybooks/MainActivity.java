package br.com.senaijandira.mybooks;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.System.*;
import java.util.ArrayList;
import java.util.Date;

import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.EstadoLivro;
import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.utils.ConvertImage;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    // Variavel de acesso ao banco de dados
    private MyBooksDatabase myBooksDb;
    private ListView listaLivro;
    /*public static ArrayList<Livro> livros = new ArrayList<Livro>();*/
    public static LivrosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(new Date().toString()+": Evento onCreate iniciado ############################################");
        setContentView(R.layout.activity_main);
        System.out.println(new Date().toString()+": Carregado o xml ############################################");

/*        myBooksDb.daoEstadoLivro().inserir(new EstadoLivro("livre"));
        myBooksDb.daoEstadoLivro().inserir(new EstadoLivro("Lido"));
        myBooksDb.daoEstadoLivro().inserir(new EstadoLivro("Lendo"));
        myBooksDb.daoEstadoLivro().getLivroEstados();*/

        /*
        *   Criando uma intancia do banco de dados usando o nome na costante da clase ConvertImage
        *
        */
        myBooksDb = Room.databaseBuilder(getApplicationContext(),MyBooksDatabase.class, ConvertImage.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        listaLivro = findViewById(R.id.ltsLivros);

        adapter = new LivrosAdapter(this);
        listaLivro.setAdapter(adapter);

        //new Thread(new Atualizalista(adapter)).start();

        System.out.println(new Date().toString()+": Evento onCreate finalizado");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.livroslidos) {
            //Abre outra janela... com livros lidos
        }else{
            //abre a janela de livros sendo lidos
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(new Date().toString()+":Evento onResume iniciado");
        adapter.clear();
        if(myBooksDb.daoEstadoLivro().getLivroEstados().length<=0) {
            long idLivre = myBooksDb.daoEstadoLivro().inserir(new EstadoLivro("Livre"));
            long idLido = myBooksDb.daoEstadoLivro().inserir(new EstadoLivro("Lido"));
            long idLendo = myBooksDb.daoEstadoLivro().inserir(new EstadoLivro("Lendo"));
        }
        Livro[] livros = myBooksDb.daoLivro().selecionarLivros();
        adapter.addAll(livros);

        EstadoLivro[] estados=myBooksDb.daoEstadoLivro().getLivroEstados();
        for(int i =0;i< estados.length;i++){
            System.out.println("Estado numero "+i);
            System.out.println("Estado id: "+estados[i].getId());
            System.out.println("Estado nome: "+estados[i].getEstado());
        }
        System.out.println(new Date().toString()+":Evento onResume finalizado");
    }
    private void removeLivro(Livro livro, View v){
        myBooksDb.daoLivro().deletar(livro);
        //remove o item do elemento layout listalivro
        //listaLivro.removeView(v);
        adapter.remove(livro);
    }
    public void abrirCadastro(View v){
        startActivity(new Intent(this,CadastroActivity.class));
    }

    public void abrirListas(View view) {
        startActivity(new Intent(this,LivrosActivity.class));
    }

    public class LivrosAdapter extends ArrayAdapter<Livro>{
        public LivrosAdapter(Context ctx) {
            super(ctx, 0, new ArrayList<Livro>());
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = convertView;

            if(v == null){
                v = LayoutInflater.from(getContext()).inflate(R.layout.livro_layout, parent, false);
            }

            final Livro livrotmp = getItem(position);

            final ImageView capa = v.findViewById(R.id.imgLivroCapa);// pega o elemento dentro da v

            TextView titulo = v.findViewById(R.id.txtLivroTitulo);

            TextView descricao = v.findViewById(R.id.txtLivroDescricao);

            ImageView iconeEdit = v.findViewById(R.id.imgEditIcon);
            ImageView iconeView = v.findViewById(R.id.imgViewIcon);
            final ImageView iconeInfo = v.findViewById(R.id.imgInfo);

            iconeEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editLivro(livrotmp,view);
                }
            });
            iconeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewLivro(livrotmp,view);
                }
            });
            iconeInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //removeLivro(livrotmp,view);
                    PopupMenu dropDownMenu = new PopupMenu(getContext(),iconeInfo);
                    dropDownMenu.getMenuInflater().inflate(R.menu.popup_menucontext,dropDownMenu.getMenu());
                    dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            System.out.println(" Usuario selecionou "+item.getTitle());
                            return true;
                        }
                    });
                    dropDownMenu.show();
                }
            });


            capa.setImageBitmap(ConvertImage.toBitmap(livrotmp.getCapa()));

            titulo.setText(livrotmp.getTitulo());

            descricao.setText(livrotmp.getDescricao());

            return v;
        }
    }
    private void viewLivro(Livro livro,View v){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("Livro", livro.getId());
        startActivity(intent);
    }
    private void editLivro(Livro livrotmp, View view) {
        Intent intent = new Intent(this, EditarActivity.class);
        intent.putExtra("Livro", livrotmp.getId());
        startActivity(intent);
    }

    private class Atualizalista implements Runnable{

        private LivrosAdapter adapter;


        public Atualizalista(final LivrosAdapter adapter){
            this.adapter = adapter;
        }

        public void run(){
            int i=0;
            while(i<9){
                try {
                    System.out.println("Hello Word | i:"+i);
                    sleep(200);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
