package com.example.crudapp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText etNomeCliente, etProduto, etQuantidade;
    private Button btnSalvar, btnListar;
    private PedidoDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNomeCliente = findViewById(R.id.etNomeCliente);
        etProduto = findViewById(R.id.etProduto);
        etQuantidade = findViewById(R.id.etQuantidade);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnListar = findViewById(R.id.btnListar);

        dbHelper = new PedidoDatabaseHelper(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeCliente = etNomeCliente.getText().toString();
                String produto = etProduto.getText().toString();
                int quantidade = Integer.parseInt(etQuantidade.getText().toString());

                long result = dbHelper.addPedido(nomeCliente, produto, quantidade);
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "Pedido salvo!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao salvar pedido!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaPedidosActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clearFields() {
        etNomeCliente.setText("");
        etProduto.setText("");
        etQuantidade.setText("");
    }
}