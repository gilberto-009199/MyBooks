package br.com.senaijandira.mybooks.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.senaijandira.mybooks.dao.EstadoLivroDao;
import br.com.senaijandira.mybooks.dao.LivroDao;
import br.com.senaijandira.mybooks.model.EstadoLivro;
import br.com.senaijandira.mybooks.model.Livro;

//@Database(entities = {Livro.class,funcionario.class},version = 1)
@Database(entities = {Livro.class,EstadoLivro.class},version = 2)
public abstract class MyBooksDatabase extends RoomDatabase {

    public abstract LivroDao daoLivro();
    public abstract EstadoLivroDao daoEstadoLivro();

}
