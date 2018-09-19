package br.com.senaijandira.mybooks.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public final class LivroLer{

    @PrimaryKey(autoGenerate=true)
    private int id;
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private int idLivro;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public void livroLido(){
        // passa o livro atual para o a tabela livros lidos

    }


}
