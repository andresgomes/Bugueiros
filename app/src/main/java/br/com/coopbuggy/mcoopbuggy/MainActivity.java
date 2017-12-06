package br.com.coopbuggy.mcoopbuggy;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.coopbuggy.mcoopbuggy.adapters.BDControle;
import br.com.coopbuggy.mcoopbuggy.adapters.ListaBugueirosAdapter;
import br.com.coopbuggy.mcoopbuggy.javaclass.Bugueiro;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BDControle banco;

    private Button btnViagem, btnCancelarViagem;
    private ListView escala;
    private Bugueiro perfilBugueiro;
    private boolean emViagem = false;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Criando perfil do bugueiro logado
        perfilBugueiro = new Bugueiro("Colossus", "aaa - 1234", R.drawable.miniperfil);
        banco = new BDControle(this);
        banco.inserir(perfilBugueiro);

        escala = (ListView) findViewById(R.id.listaBugueiros);
        List<Bugueiro> bugueiros = gerarBugueiros();

        final ListaBugueirosAdapter adapter = new ListaBugueirosAdapter(this, bugueiros);
        escala.setAdapter(adapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Iniciar e terminar viagem
        btnViagem = (Button) findViewById(R.id.btnInicarCorrida);
        btnCancelarViagem = (Button) findViewById(R.id.btnCancelarCorrida);
        btnCancelarViagem.setVisibility(View.INVISIBLE);
        btnViagem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnViagem.setText("Concluir viagem");
                if (btnViagem.getText().equals("Iniciar Viagem")) {
                    btnViagem.setText("Concluir Viagem");
                    btnCancelarViagem.setVisibility(View.VISIBLE);
                    Timer timer = new Timer();
                    timerTask = new TimerTask() {
                        int contador = 0;
                        @Override
                        public void run() {
                            Log.i("TesteTimer", "Mensagem: " + contador);
                            contador ++;
                        }
                    };
                    timer.schedule(timerTask, 0, 5000);
                    emViagem = true;
                }
                else {
                    btnViagem.setText("Iniciar Viagem");
                    btnCancelarViagem.setVisibility(View.INVISIBLE);
                    timerTask.cancel();
                    Log.i("TesteTimer", "Contador parado");
                    emViagem = false;
                }
            }
        });

        /*
        Fim do OnCreate
         */
    }

    private List<Bugueiro> gerarBugueiros() {
        List<Bugueiro> arrayBugueiros = new ArrayList<Bugueiro>();

        arrayBugueiros.add(criarBugueiro("Andre", "aaa-0000", R.drawable.miniperfil));
        arrayBugueiros.add(criarBugueiro("Handerson", "aaa-0000", R.drawable.miniperfil));
        arrayBugueiros.add(criarBugueiro("Raphael", "aaa-0000", R.drawable.miniperfil));
        arrayBugueiros.add(criarBugueiro("Gustavo", "aaa-0000", R.drawable.miniperfil));
        arrayBugueiros.add(criarBugueiro("João", "aaa-0000", R.drawable.miniperfil));
        arrayBugueiros.add(criarBugueiro("Antonio", "aaa-0000", R.drawable.miniperfil));
        arrayBugueiros.add(criarBugueiro("Jose", "aaa-0000", R.drawable.miniperfil));
        arrayBugueiros.add(criarBugueiro("ALfredo", "aaa-0000", R.drawable.miniperfil));
        arrayBugueiros.add(criarBugueiro("Matias", "aaa-0000", R.drawable.miniperfil));

        return arrayBugueiros;
    }

    private Bugueiro criarBugueiro(String nome, String placa, int image){
        Bugueiro bugueiro = new Bugueiro(nome, placa, image);

        return bugueiro;
    }

    /*
    Fim dos metodos proprios
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sair?");
            builder.setMessage("Deseja sair e fechar a aplicação? \n" +
                    "Você perderá os dados não salvos.");
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intentPerfil = new Intent(MainActivity.this, PerfilActivity.class);
//            intentPerfil.putExtra("perfilBugueiro", perfilBugueiro);
            startActivity(intentPerfil);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_precos) {
            Intent intentPrecos = new Intent(MainActivity.this, PrecoActivity.class);
            startActivity(intentPrecos);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

class emViagem extends AsyncTask<String, Void, Uri>{

    @Override
    protected Uri doInBackground(String... strings) {
        return null;
    }
}
