package com.example.saulosinesio.atividaderec_pdm_saulosinesio.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saulosinesio.atividaderec_pdm_saulosinesio.R;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.validate.Different;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.validate.Email;
import com.example.saulosinesio.atividaderec_pdm_saulosinesio.validate.Empty;

public class Login extends AppCompatActivity {

    private EditText etNome, etEmail, etSenha, etConfirmar;
    private Button btnFechar, btnCadastrar;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        binding();

        sharedPreferences = getSharedPreferences("SaveInfo", MODE_PRIVATE);

        String nome = sharedPreferences.getString("nome", null);
        String senha = sharedPreferences.getString("senha", null);

        if (nome != null && senha != null) {
            // Redirecionando para a Activity Home
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
            finish();
        } else {
            ativarListener();
        }
    }

    /**
     * Ativa o Listener para a aplicação
     */
    private void ativarListener(){
        View.OnClickListener cadastrar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dados Usuário
                String nome = etNome.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();
                String confirmar = etConfirmar.getText().toString().trim();

                // Validações
                if (Empty.isEmpty(nome)) {
                    Toast toast = Toast.makeText(Login.this, "O nome deve ser preenchido", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (Empty.isEmpty(email)) {
                    Toast toast = Toast.makeText(Login.this, "O email deve ser preenchido", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (!Email.isEmail(email)) {
                    Toast toast = Toast.makeText(Login.this, "O email não é válido", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (Empty.isEmpty(senha) || Empty.isEmpty(confirmar)) {
                    Toast toast = Toast.makeText(Login.this, "a senha e a confirmação deve ser preenchidas", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (Different.isDifferent(senha, confirmar)) {
                    Toast toast = Toast.makeText(Login.this, "A senha deve ser igual a confirmação de senha", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                // Salvando informações
                editor = sharedPreferences.edit();

                editor.putString("nome", nome);
                editor.putString("senha", senha);
                editor.apply();

                // Redirecionando para a Activity Home
                Intent i = new Intent(Login.this, Home.class);
                startActivity(i);
                finish();
            }
        };
        btnCadastrar.setOnClickListener(cadastrar);

        View.OnClickListener fechar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        btnFechar.setOnClickListener(fechar);
    }

    /**
     * Binding XML login
     */
    private void binding () {
        // Edit Text
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etConfirmar = findViewById(R.id.etConfirmar);
        // Button
        btnFechar = findViewById(R.id.btnFechar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }
}
