package br.com.coopbuggy.mcoopbuggy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import br.com.coopbuggy.mcoopbuggy.adapters.VolleyAdapter;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String ENDPOINT = "https://bugueiros.herokuapp.com/api/login/";
    // UI references.
    private EditText mPasswordView, mUserName;
    private View mProgressView;
    private View mLoginFormView;
    private RequestQueue requestQueue;
    private Map<String, String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestQueue = Volley.newRequestQueue(this);

        // Set up the login form.
        mUserName = (EditText) findViewById(R.id.txtUserName);
        mPasswordView = (EditText) findViewById(R.id.txtPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName = mUserName.getText().toString();
                String Pass = mPasswordView.getText().toString();
                params = new HashMap<String, String>();
                params.put("username", UserName);
                params.put("password", Pass);
                loginVolley(params);
//                if(UserName.equalsIgnoreCase("andre") && Pass.equals("123")){
//                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(mainIntent);
//                    Toast.makeText(LoginActivity.this, "Bem vindo " + mUserName.getText(), Toast.LENGTH_SHORT).show();
//                    LoginActivity.this.finish();
//                }else {
//                    Toast.makeText(LoginActivity.this, "Desculpe. Verifique os dados de acesso.", Toast.LENGTH_SHORT).show();
//                }
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

    private void loginVolley(Map<String, String> params){
        VolleyAdapter request = new VolleyAdapter(Method.POST,ENDPOINT, params, onPostLoaded, onPostError);;
        Log.i("ResponseTeste",String.valueOf(request));
        requestQueue.add(request);
    }

    private final Response.Listener<String> onPostLoaded = new Response.Listener<String>(){
        @Override
        public void onResponse(String response) {
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            Toast.makeText(LoginActivity.this, "Bem vindo " + mUserName.getText(), Toast.LENGTH_SHORT).show();
        }
    };

    private final Response.ErrorListener onPostError = new ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(LoginActivity.this, "Desculpe. Verifique os dados de acesso.", Toast.LENGTH_SHORT).show();
        }
    };

}

