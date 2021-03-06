package br.com.coopbuggy.mcoopbuggy.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andre on 03/12/2017.
 */

public class BDCore extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "bugueiro";
    private static final String ID = "_id";
    private static final String NOME = "nome";
    private static final String TELEFONE = "telefone";
    private static final String EMAIL = "email";
    private static final String PLACAVEICULO = "placa";
    private static final String FOTOPERFIL = "foto";
    private static final int VERSAO = 3;

    public BDCore(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE bugueiro(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " first_name TEXT, last_name TEXT, email TEXT, placa TEXT, foto TEXT,int PERMISSION, token TOKEN)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE bugueiro");
        onCreate(sqLiteDatabase);
    }
}
