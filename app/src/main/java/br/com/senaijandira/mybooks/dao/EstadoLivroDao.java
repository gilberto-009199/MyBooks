package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import br.com.senaijandira.mybooks.model.EstadoLivro;
import br.com.senaijandira.mybooks.model.Livro;
@Dao
public interface EstadoLivroDao {
    @Insert
    long  inserir(EstadoLivro l);
    @Update
    void atualizar(EstadoLivro l);

    @Delete
    void deletar(EstadoLivro l);

    @Query("SELECT * FROM estadolivros")
    EstadoLivro[] getLivroEstados();

}
