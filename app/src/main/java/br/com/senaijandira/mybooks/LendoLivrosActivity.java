package br.com.senaijandira.mybooks;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.utils.Alertas;
import br.com.senaijandira.mybooks.utils.ConvertImage;

public class LendoLivrosActivity extends AppCompatActivity {

    private MyBooksDatabase myBooksDb;
    private ListView listaLivro;
    public static LivrosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lendo_livros);

        myBooksDb = Room.databaseBuilder(getApplicationContext(),MyBooksDatabase.class, ConvertImage.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        listaLivro = findViewById(R.id.ltsLivros);

        adapter = new LivrosAdapter(this);
        listaLivro.setAdapter(adapter);


    }
    protected void onResume() {
        super.onResume();
        System.out.println(new Date().toString()+":Evento onResume iniciado");
        adapter.clear();
        Livro[] livros = myBooksDb.daoLivro().selecionarLivros("lendo");
        adapter.addAll(livros);
        System.out.println(new Date().toString()+":Evento onResume finalizado");
    }

    public void abrirListas(View view) {
        startActivity(new Intent(this,LidosLivrosActivity.class));
    }
    public void abrirListas(String lista) {
        if(lista.equals("lidos")){
            startActivity(new Intent(this,LidosLivrosActivity.class));
        }
    }


    public class LivrosAdapter extends ArrayAdapter<Livro> {
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
                    dropDownMenu.getMenu().getItem(1).setVisible(false);
                    dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            System.out.println(" Usuario selecionou "+item.getTitle());
                            switch(item.getTitle().toString()){
                                case "Enviar para Lidos":
                                    System.out.println(" Enviando livro para lidos "+item.getTitle());
                                    livrotmp.setEstado("lido");
                                    myBooksDb.daoLivro().atualizar(livrotmp);
                                    onResume();
                                    break;
                                case "Excluir Livro":
                                    Alertas.Alerta(getContext(), "O livro já pode ser Deletado!!", "O livro pode ser deletado,Deseja deletar agora?", new Dialog.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            myBooksDb.daoLivro().deletar(livrotmp);
                                            onResume();
                                        }
                                    }, new Dialog.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            livrotmp.setEstado("livre");
                                            myBooksDb.daoLivro().atualizar(livrotmp);
                                            onResume();
                                        }
                                    });
                            }

                            return true;
                        }
                    });
                    dropDownMenu.show();
                }
            });

            System.out.println(" Estado do livro: "+livrotmp.getEstado());
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
}
