package br.com.senaijandira.mybooks;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import br.com.senaijandira.mybooks.fragments.LivrosLidos;

public class LivrosActivity extends AppCompatActivity {

    private TabLayout tabs;
    private FrameLayout Quadro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);
        tabs = findViewById(R.id.menutab);
        Quadro = findViewById(R.id.Quadro);

        tabs.addTab(tabs.newTab().setText("Lidos"));
        tabs.addTab(tabs.newTab().setText("Lendo"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println(" Quer ver a lista de livros"+tab.getText());
                verLidos(null);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                System.out.println("unselected");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        System.out.println(" onCreate finalizado");
    }

    public void verLidos(View view) {
        System.out.println(" Quer ver a lista de livros que foram lidos");
    }

    public void verLendos(View view) {
        System.out.println(" Quer ver a lista de livros que estam sendo lidos");
    }
}
