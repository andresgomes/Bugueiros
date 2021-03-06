package br.com.coopbuggy.mcoopbuggy.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.coopbuggy.mcoopbuggy.javaclass.Bugueiro;

/**
 * Created by Andre on 04/12/2017.
 */

public class BDControle {
    private SQLiteDatabase database;

    public BDControle(Context context){
        BDCore bd = new BDCore(context);
        database = bd.getWritableDatabase();
    }

    public void inserir(Bugueiro bugueiro){
        ContentValues values = new ContentValues();
        values.put("first_name", bugueiro.getFirstName());
        values.put("placa", bugueiro.getPlacaVeiculo());

        database.insert("bugueiro", null, values);
    }

    public void atualizar(Bugueiro bugueiro){
        ContentValues values = new ContentValues();
        values.put("first_name", bugueiro.getFirstName());
        values.put("last_name", bugueiro.getLast_name());
        values.put("foto", bugueiro.getImagemSerializada());

        database.update("bugueiro", values, "_id = ?", new String[]{""+bugueiro.getId()});
    }

    public void deletar(Bugueiro bugueiro){
        database.delete("bugueiro", "_id = " +bugueiro.getId(),null);
    }

    public Bugueiro buscar(){
        Bugueiro bugueiro = new Bugueiro();
        String[] colunas = new String[]{"_id", "first_name", "last_name", "foto"};

        Cursor cursor = database.query("bugueiro", colunas, null, null, null, null,null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            bugueiro.setId((int) cursor.getLong(0));
            bugueiro.setFirstName(cursor.getString(1));
            bugueiro.setLast_name(cursor.getString(2));
            bugueiro.setImagemSerializada(cursor.getString(3));

        }

        return (bugueiro);
    }
}
