package com.example.prova2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova2.R;
import com.example.prova2.bd.BDProduto;
import com.example.prova2.model.Carrinho;
import com.example.prova2.model.Pedido;
import com.example.prova2.model.Produto;

import java.util.ArrayList;

public class DetalhesPedidoAdapter extends RecyclerView.Adapter<DetalhesPedidoAdapter.ViewHolder> {
    private final ArrayList<Produto> lista;
    private final Context context;
    private final BDProduto bdProduto;
    private final ArrayList<String> qtdProdutos;

    public DetalhesPedidoAdapter(Context context, ArrayList<Produto> lista, ArrayList<String> qtdProdutos) {
        Log.i("debug", "aqui entrou no adapter");
        this.lista = lista;
        this.context = context;
        this.bdProduto = BDProduto.getInstance(context);
        this.qtdProdutos = qtdProdutos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.detalhes_pedido_vertical, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getImg().setImageBitmap(lista.get(position).getImg());
        holder.getNomeCarrinho().setText(lista.get(position).getNome());
        Log.i("debug", "nome do produto: " + lista.get(position).getNome());
        holder.getPrecoUnitario().setText("R$ " + lista.get(position).getPreco().toString());
        Log.i("debug", "preço do produto: " + lista.get(position).getPreco().toString());

        holder.getQuantidadeCarrinho().setText("x" + qtdProdutos.get(position));
        Log.i("debug", "quantidade do produto: " + qtdProdutos.get(position));


        // Falta fazer preço total de cada produto


    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView nomeCarrinho;
        private final TextView precoUnitario;
        private final TextView quantidadeCarrinho;
        // private final TextView precoTotalProduto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.i("debug", "aqui entrou no viewholder");
            this.img = (ImageView) itemView.findViewById(R.id.img_cardapio_produto);
            this.nomeCarrinho = (TextView) itemView.findViewById(R.id.txt_nome_carrinho);
            this.precoUnitario = (TextView) itemView.findViewById(R.id.txt_preco_unitario);
            this.quantidadeCarrinho = (TextView) itemView.findViewById(R.id.txt_quantidade_carrinho);
            //  this.precoTotalProduto = (TextView) itemView.findViewById(R.id.txt_preco_total_produto);
        }

        public ImageView getImg() {
            return img;
        }

        public TextView getNomeCarrinho() {
            return nomeCarrinho;
        }

        public TextView getPrecoUnitario() {
            return precoUnitario;
        }

        public TextView getQuantidadeCarrinho() {
            return quantidadeCarrinho;
        }

//        public TextView getPrecoTotalProduto() {
//            return precoTotalProduto;
//        }
    }
}