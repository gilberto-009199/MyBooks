package br.com.senaijandira.mybooks.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import br.com.senaijandira.mybooks.model.Livro;

@Dao
public interface LivroDao {

    @Insert
    void inserir(Livro l);

    @Query("SELECT *FROM livro where id = :i ")
    Livro getLivro(int i);

    @Update
    void atualizar(Livro l);

    @Delete
    void deletar(Livro l);

    @Query("SELECT * FROM livro")
    Livro[] selecionarLivros();


    @Query("SELECT * FROM livro where Estado =:estado ")
    Livro[] selecionarLivros(String estado);

    @Query("SELECT * FROM livro where titulo like :descri or descricao like :descri")
    Livro[] pesquisaLivros(String descri);

}
