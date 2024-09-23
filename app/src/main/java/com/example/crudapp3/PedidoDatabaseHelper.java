package com.example.crudapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PedidoDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pedidos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "pedidos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME_CLIENTE = "nome_cliente";
    private static final String COLUMN_PRODUTO = "produto";
    private static final String COLUMN_QUANTIDADE = "quantidade";

    public PedidoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME_CLIENTE + " TEXT, " +
                COLUMN_PRODUTO + " TEXT, " +
                COLUMN_QUANTIDADE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public long addPedido(String nomeCliente, String produto, int quantidade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME_CLIENTE, nomeCliente);
        values.put(COLUMN_PRODUTO, produto);
        values.put(COLUMN_QUANTIDADE, quantidade);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }


    public Cursor getAllPedidos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }


    public int updatePedido(int id, String nomeCliente, String produto, int quantidade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME_CLIENTE, nomeCliente);
        values.put(COLUMN_PRODUTO, produto);
        values.put(COLUMN_QUANTIDADE, quantidade);

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Função para deletar um pedido
    public void deletePedido(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
