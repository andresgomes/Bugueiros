package br.com.coopbuggy.mcoopbuggy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private EditText mPasswordView, mUserName;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mUserName = (EditText) findViewById(R.id.txtUserName);
        mPasswordView = (EditText) findViewById(R.id.txtPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName = mUserName.getText().toString();
                String Pass = mPasswordView.getText().toString();
                if(UserName.equalsIgnoreCase("andre") && Pass.equals("123")){
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    Toast.makeText(LoginActivity.this, "Bem vindo " + mUserName.getText(), Toast.LENGTH_SHORT).show();
                    LoginActivity.this.finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Desculpe. Verifique os dados de acesso.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnEsqueciSenha = (Button) findViewById(R.id.btnEsqueciSenha);
        btnEsqueciSenha.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(LoginActivity.this, EsqueciSenhaActivity.class);
                startActivity((MainIntent));
                Toast.makeText(LoginActivity.this,"Digite o email para recuperação", Toast.LENGTH_LONG).show();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

}

