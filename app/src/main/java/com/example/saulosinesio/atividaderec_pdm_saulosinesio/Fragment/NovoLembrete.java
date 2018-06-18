package com.example.saulosinesio.atividaderec_pdm_saulosinesio.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saulosinesio.atividaderec_pdm_saulosinesio.R;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.activity.Home;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.clases.Singleton;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.validate.Empty;

public class NovoLembrete extends Fragment {

    private EditText etNotaDescricao, etNotaNome;
    private TextView tvQuantidadeMax;
    private Button btnSalvar;

    private FloatingActionButton fab;
    private int maxSize = 250;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.novo_lembrete, container, false);

        etNotaNome = view.findViewById(R.id.etNotaNome);
        etNotaDescricao = view.findViewById(R.id.etNotaDescricao);
        tvQuantidadeMax = view.findViewById(R.id.tvQuantidadeMax);
        btnSalvar = view.findViewById(R.id.btnSalvar);

        // Escondendo o Fab Buttom
        fab = ((Home)getActivity()).getFab();
        fab.hide();

        // Verificar Quantidades de Caracteres.
        etNotaDescricao.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                int qtd = maxSize;                                          // Valor Atual.
                int size = etNotaDescricao.getText().toString().length();   // Tamanho do Texto.
                size = qtd - size;
                tvQuantidadeMax.setText(Integer.toString(size));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo = etNotaNome.getText().toString().trim();
                String descricao = etNotaDescricao.getText().toString().trim();

                if(Empty.isEmpty(titulo) || Empty.isEmpty(descricao)){
                    Toast toast = Toast.makeText(NovoLembrete.super.getContext(), "O título e a descrição devem ser preenchidos", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else {
                    // Verifica se o Lembrete tem mais do que 250 Caracteres.
                    if ( etNotaDescricao.getText().toString().length() > maxSize ) {
                        Toast toast = Toast.makeText(NovoLembrete.super.getContext(), "O lembrete não pode ter mais do que 250 Caracteres", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    } else {
                        Singleton.getInstance().addNota(titulo, descricao);
                        Snackbar.make(v, "Lembrete Salvo com Sucesso!", Snackbar.LENGTH_SHORT).setAction("Ação", null).show();
                        fab.show();

                        ListaLembretes frag = new ListaLembretes();
                        getFragmentManager().beginTransaction().replace(R.id.home_container, frag).commit();

                        return;
                    }
                }
            }
        };
        btnSalvar.setOnClickListener(listener);

        return view;
    }
}
