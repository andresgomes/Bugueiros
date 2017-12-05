package br.com.coopbuggy.mcoopbuggy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import br.com.coopbuggy.mcoopbuggy.adapters.BDControle;
import br.com.coopbuggy.mcoopbuggy.javaclass.Bugueiro;

public class PerfilActivity extends AppCompatActivity {

    private BDControle banco;
    private Button btnVoltaDePerfil,btnSalvar;
    private EditText nome, sobrenome;
    private ImageButton btnAlterarFoto;
    private Bugueiro perfilBugueiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //perfilBugueiro = (Bugueiro) getIntent().getSerializableExtra("perfilBugueiro");

        banco = new BDControle(this);
        perfilBugueiro = banco.buscar();

        btnAlterarFoto = (ImageButton) findViewById(R.id.botaoAlterarFoto);

        //carregando imagem de perfil caso haja
        if (perfilBugueiro.getImagemSerializada() != null){
            String desconverterImagem = perfilBugueiro.getImagemSerializada();
            byte dados[] = android.util.Base64.decode(desconverterImagem,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(dados, 0,dados.length);
            btnAlterarFoto.setImageBitmap(bitmap);
        }


        //Iniciar Alteração de foto
        btnAlterarFoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PerfilActivity.this);
                builder.setTitle("Alterar foto:");
                builder.setMessage("Usar camera ou carregar da galeria?");
                builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(PerfilActivity.this,"Carregando camera",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(PerfilActivity.this,"Carregando galeria",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //Salvando alteração de perfil
        btnSalvar = (Button) findViewById(R.id.btnSalvarAlteracaoPerfil);
        btnSalvar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
                perfilBugueiro.setNome(nome.getText().toString());
                banco.atualizar(perfilBugueiro);
                startActivity(intent);
            }
        });

        //Recuperando do laytout e exibindo
        nome = (EditText) findViewById(R.id.edtNome);
        sobrenome = (EditText) findViewById(R.id.edtSobrenome);
        nome.setText(perfilBugueiro.getNome());


        //Ação do botao voltar do app
        btnVoltaDePerfil = (Button) findViewById(R.id.btnVoltaDePerfil);
        btnVoltaDePerfil.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar = new Intent(PerfilActivity.this, MainActivity.class);
                startActivity(voltar);
            }
        });
    }

    //Tratar retorno da imagem
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Testar retorno
        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
            //Recuperar local da imagem
            Uri localImagemSelecionada = data.getData();

            try {
                Bitmap imagem = Media.getBitmap(getContentResolver(),
                        localImagemSelecionada);

                //Comprimir no formato PNG
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(CompressFormat.JPEG, 75, stream);

                btnAlterarFoto.setImageBitmap(imagem);

                byte[] bytesImagem = stream.toByteArray();
                String imagemConvertida = android.util.Base64.encodeToString(bytesImagem, Base64.DEFAULT);
                perfilBugueiro.setImagemSerializada(imagemConvertida);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(PerfilActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
