package com.example.saulosinesio.atividaderec_pdm_saulosinesio.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.saulosinesio.atividaderec_pdm_saulosinesio.R;

public class Sobre extends AppCompatActivity {

    private Button btnSiteSenac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobre);

        btnSiteSenac = findViewById(R.id.btnSiteSenac);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String site = "https://www.sp.senac.br/jsp/default.jsp?newsID=DYNAMIC,oracle.br.dataservers.CourseDataServer,selectCourse&course=10600&template=409.dwt&unit=NONE&testeira=1415&type=G&sub=1";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(site));
                startActivity(i);
            }
        };
        btnSiteSenac.setOnClickListener(listener);

        // Botão Voltar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
