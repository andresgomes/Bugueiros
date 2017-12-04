package br.com.coopbuggy.mcoopbuggy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EsqueciSenhaActivity extends AppCompatActivity {

    private Button btnRecAcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        btnRecAcesso = (Button) findViewById(R.id.btnRecuperarAcesso);

        btnRecAcesso.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EsqueciSenhaActivity.this, "Email para recuperação enviado!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EsqueciSenhaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
