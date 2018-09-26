package br.com.senaijandira.mybooks;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.utils.ConvertImage;

public class EditarActivity extends AppCompatActivity {
    private MyBooksDatabase myBooksDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        myBooksDb = Room.databaseBuilder(getApplicationContext(),MyBooksDatabase.class, ConvertImage.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        int i= getIntent().getIntExtra("Livro",0);
        System.out.println(" id:"+i);

        Livro livrotmp =  myBooksDb.daoLivro().getLivro(i);


    }



}
