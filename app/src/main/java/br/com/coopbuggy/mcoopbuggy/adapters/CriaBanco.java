package br.com.coopbuggy.mcoopbuggy.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andre on 03/12/2017.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "bugueiro";
    private static final String ID = "_id";
    private static final String NOME = "nome";
    private static final String TELEFONE = "telefone";
    private static final String EMAIL = "email";
    private static final String PLACAVEICULO = "placa";
    private static final int VERSAO = 1;

    public CriaBanco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
