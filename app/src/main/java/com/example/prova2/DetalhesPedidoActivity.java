package com.example.prova2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova2.adapter.DetalhesPedidoAdapter;
import com.example.prova2.bd.BDCarrinho;
import com.example.prova2.bd.BDPedido;
import com.example.prova2.bd.BDProduto;
import com.example.prova2.bd.BDUsuario;
import com.example.prova2.interfaces.botaoVoltar;
import com.example.prova2.model.Carrinho;
import com.example.prova2.model.Pedido;
import com.example.prova2.model.Produto;
import com.example.prova2.model.Usuario;

import java.util.ArrayList;

public class DetalhesPedidoActivity extends AppCompatActivity implements botaoVoltar {
    BDPedido bdPedidos;
    BDProduto bdProduto;
    RecyclerView rvlista;
    String idPedido;
    String enderecoEntrega;
    Pedido pedidoDetalhe;
    String[] idsProdutos;
    String[] nomesProdutos;
    ArrayList<String> qtdProdutos = new ArrayList<>();
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    TextView precoTotal, endereco, formaPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pedido);
        Log.i("logi", "aqui é detalhepedidoactivy ");

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            idPedido = null;
            enderecoEntrega = null;
        } else {
            idPedido = extras.getString("ID_PEDIDO");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        bdPedidos = BDPedido.getInstance(this);

        pedidoDetalhe = bdPedidos.findByID(idPedido);
        nomesProdutos = pedidoDetalhe.getNomesProdutos().split(", ");
        idsProdutos = pedidoDetalhe.getIdsProdutos().split(",");

        bdProduto = BDProduto.getInstance(this);
        for (String s :
                idsProdutos) {
            listaProdutos.add(bdProduto.findByID(s));
        }

        for (String s :
                nomesProdutos) {
            String[] x = s.split("\\(");
            qtdProdutos.add(x[1].replace(")", ""));
        }

        rvlista = (RecyclerView) findViewById(R.id.rvListaCarrinho);
        rvlista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        DetalhesPedidoAdapter detalhesPedidoAdapter = new DetalhesPedidoAdapter(this, listaProdutos, qtdProdutos, pedidoDetalhe);
        rvlista.setAdapter(detalhesPedidoAdapter);

        precoTotal = findViewById(R.id.txt_carrinho_valor_total);
        endereco = findViewById(R.id.txt_endereco_entrega);
        formaPagamento = findViewById(R.id.txt_forma_pagamento);

        precoTotal.setText(pedidoDetalhe.getValorFinal());
        endereco.setText(pedidoDetalhe.getEnderecoEntrega());
        formaPagamento.setText(pedidoDetalhe.getFormaPagamento());

    }

    @Override
    public void onBackPressed() {

        Usuario u = BDUsuario.getInstance(this).findByID(pedidoDetalhe.getIdUsuario());
//        Toast.makeText(this, u.getNome(), Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, Home.class);
        it.putExtra("ID_USUARIO", u.getId());
        startActivity(it);
    }

}