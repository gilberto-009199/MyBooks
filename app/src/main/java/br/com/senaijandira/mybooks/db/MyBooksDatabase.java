package br.com.senaijandira.mybooks.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.senaijandira.mybooks.dao.LivroDao;
import br.com.senaijandira.mybooks.dao.LivroLerDao;
import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.model.LivroLer;

//@Database(entities = {Livro.class,funcionario.class},version = 1)
@Database(entities = {Livro.class, LivroLer.class},version = 1)
public abstract class MyBooksDatabase extends RoomDatabase {

    public abstract LivroDao daoLivro();
    public abstract LivroLerDao daoLivroLendo();
}
