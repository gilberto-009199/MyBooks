package br.com.senaijandira.mybooks.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.senaijandira.mybooks.R;

/**
 * Created by 16254850 on 01/10/2018.
 */

public class LivrosLidos extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_livroslidos, container, false);
    }
}
