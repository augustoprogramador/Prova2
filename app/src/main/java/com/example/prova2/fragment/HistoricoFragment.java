package com.example.prova2.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prova2.DetalhesPedidoActivity;
import com.example.prova2.Home;
import com.example.prova2.R;
import com.example.prova2.adapter.HistoricoPedidoAdapter;
import com.example.prova2.adapter.PedidoAdapterVertical;
import com.example.prova2.bd.BDPedido;
import com.example.prova2.bd.BDProduto;
import com.example.prova2.model.Pedido;

import java.util.ArrayList;
import java.util.Collections;


public class HistoricoFragment extends Fragment {
    RecyclerView rvListaHistorico;
    BDPedido bdPedido;
    HistoricoPedidoAdapter adapter;

    String idUsuario;

    ArrayList<Pedido> historico;

    public HistoricoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idUsuario = ((Home)getActivity()).getUserId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historico, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bdPedido = bdPedido.getInstance(getContext());

        historico = bdPedido.findAll(idUsuario);
        Collections.reverse(historico);

        rvListaHistorico = (RecyclerView) view.findViewById(R.id.rvListaHistorico);
        rvListaHistorico.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        createAdapter();

        rvListaHistorico.setAdapter(adapter);
    }

    public void createAdapter(){
        adapter = new HistoricoPedidoAdapter(getContext(), historico, new HistoricoPedidoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Pedido pedido) {
                Log.i("debug", "evento de clique funcionando");
                Intent intent = new Intent(getContext(), DetalhesPedidoActivity.class);
                intent.putExtra("ID_PEDIDO", pedido.getId());
                startActivity(intent);
            }
        });
    }
}