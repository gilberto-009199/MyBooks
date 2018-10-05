package br.com.senaijandira.mybooks;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LivrosActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);
    }

    public void verLidos(View view) {
        System.out.println(" Quer ver a lista de livros que foram lidos");
    }

    public void verLendos(View view) {
        System.out.println(" Quer ver a lista de livros que estam sendo lidos");
    }
}
