package com.example.prova2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova2.adapter.DetalhesPedidoAdapter;
import com.example.prova2.bd.BDCarrinho;
import com.example.prova2.bd.BDPedido;
import com.example.prova2.bd.BDProduto;
import com.example.prova2.model.Carrinho;
import com.example.prova2.model.Pedido;
import com.example.prova2.model.Produto;

import java.util.ArrayList;

public class DetalhesPedidoActivity extends AppCompatActivity {
    BDCarrinho bdCarrinho;
    BDPedido bdPedidos;
    BDProduto bdProduto;
    RecyclerView rvlista;
    String idPedido;
    String enderecoEntrega;
    ArrayList<Carrinho> carrinhos = new ArrayList<>();
    ArrayList<Pedido> pedido = new ArrayList<>();
    Pedido pedidoDetalhe;
    String[] idsProdutos;
    String[] nomesProdutos;
    ArrayList<String> qtdProdutos = new ArrayList<>();
    ArrayList<Produto> listaProdutos = new ArrayList<>();

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
//            enderecoEntrega = extras.getString("ENDERECO_ENTREGA");
//            Log.i("logi", "endereço de entrega: "+ enderecoEntrega);
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
    }
}