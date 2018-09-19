package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import br.com.senaijandira.mybooks.model.LivroLer;

@Dao
public interface LivroLerDao {

    @Insert
    void inserir(LivroLer l);

    @Update
    void atualizar(LivroLer l);

    @Delete
    void deletar(LivroLer l);

    @Query("SELECT * FROM livroLer ")
    LivroLer[] selecionarLivrosLidos();

}
