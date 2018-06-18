package com.example.saulosinesio.atividaderec_pdm_saulosinesio.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saulosinesio.atividaderec_pdm_saulosinesio.R;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.activity.Home;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.clases.Nota;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.clases.Singleton;

import java.util.List;

public class ListaLembretes extends Fragment {

    private ViewGroup cardViewGroup;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_lembretes, container, false);

        // Verificando se existe lembrentes.. (Caso não, informar uma o usuário).
        if( Singleton.getInstance().getListNotas().size() == 0 ){
            SemLembrete frag = new SemLembrete();
            getFragmentManager().beginTransaction().replace(R.id.home_container, frag).commit();
        } else {
            // Escondendo o Fab Buttom
            fab = ((Home)getActivity()).getFab();
            fab.show();

            cardViewGroup = view.findViewById(R.id.containerLista);

            List<Nota> notas = Singleton.getInstance().getListNotas();

            if (notas.size() > 0) {
                for ( int i = 0; i < notas.size(); i++) {
                    addItem(notas.get(i).getTitulo(), notas.get(i).getNota(), notas.get(i).getIndex());
                }
            }
        }
        return view;
    }

    /**
     * Cria um CardView
     * @param titulo
     * @param descricao
     */
    private void addItem (String titulo, String descricao, final int index) {
        CardView cardView = (CardView) getLayoutInflater().inflate(R.layout.cardview_notas, cardViewGroup, false);

        TextView etTitulo = cardView.findViewById(R.id.etTitulo);
        TextView etDescricao = cardView.findViewById(R.id.etDescricao);
        final TextView tvIndex = cardView.findViewById(R.id.tvIndex);
        Button btnDeletarLembrete = cardView.findViewById(R.id.btnDeletarLembrete);

        etTitulo.setText(titulo);
        etDescricao.setText(descricao);
        tvIndex.setText(Integer.toString(index ));

        // Listener Para cada lembrete.
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Listener do Dialogo
                DialogInterface.OnClickListener confirmarcao = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<Nota> notas = Singleton.getInstance().getListNotas();
                        notas.remove(index);
                        // Recarregar Página.
                        ListaLembretes frag = new ListaLembretes();
                        getFragmentManager().beginTransaction().replace(R.id.home_container, frag).commit();
                    }
                };

                // Solicitar Confrimação.
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaLembretes.super.getContext());
                builder.setMessage("Deseja deletar este lembrete?");
                builder.setTitle("Atenção");
                builder.setCancelable(true);
                builder.setNegativeButton("Cancelar", null);
                builder.setPositiveButton("Deletar", confirmarcao);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
        btnDeletarLembrete.setOnClickListener(listener);

        cardViewGroup.addView(cardView);
    }
}
