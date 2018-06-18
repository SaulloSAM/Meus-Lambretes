package com.example.saulosinesio.atividaderec_pdm_saulosinesio.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saulosinesio.atividaderec_pdm_saulosinesio.R;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.activity.Home;

public class SemLembrete extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sem_lembrete, container, false);

        FloatingActionButton fab;
        fab = ((Home)getActivity()).getFab();
        fab.show();

        return view;
    }
}
