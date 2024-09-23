package com.example.crudapp3;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListaPedidosActivity extends AppCompatActivity {

    private ListView listViewPedidos;
    private PedidoDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        listViewPedidos = findViewById(R.id.listViewPedidos);
        dbHelper = new PedidoDatabaseHelper(this);

        loadPedidos();
    }

    private void loadPedidos() {
        ArrayList<String> pedidosList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllPedidos();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nomeCliente = cursor.getString(1);
                String produto = cursor.getString(2);
                int quantidade = cursor.getInt(3);
                pedidosList.add(id + ": " + nomeCliente + " - " + produto + " (" + quantidade + ")");
            } while (cursor.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pedidosList);
        listViewPedidos.setAdapter(adapter);
    }
}