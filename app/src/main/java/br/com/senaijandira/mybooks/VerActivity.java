package br.com.senaijandira.mybooks;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.utils.ConvertImage;

public class VerActivity extends AppCompatActivity {
    private MyBooksDatabase myBooksDb;
    private Livro livrotmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        myBooksDb = Room.databaseBuilder(getApplicationContext(),MyBooksDatabase.class, ConvertImage.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        int i= getIntent().getIntExtra("Livro",0);
        System.out.println(" id:"+i);

        livrotmp =  myBooksDb.daoLivro().getLivro(i);

        ImageView capa = findViewById(R.id.imgLivroCapa);

        TextView titulo = findViewById(R.id.txtTitulo);

        TextView descricao = findViewById(R.id.txtDesc);

        titulo.setText(livrotmp.getTitulo());
        descricao.setText(livrotmp.getDescricao());
        capa.setImageBitmap(ConvertImage.toBitmap(livrotmp.getCapa()));

    }


    public void Voltar(View view) {
        finish();
    }

    public void Editar(View view) {
        Intent intent = new Intent(this, EditarActivity.class);
        intent.putExtra("Livro", livrotmp.getId());
        startActivity(intent);
    }
}
