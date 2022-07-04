package com.example.prova2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prova2.Home;
import com.example.prova2.MainActivity;
import com.example.prova2.R;
import com.example.prova2.bd.BDUsuario;
import com.example.prova2.model.Usuario;

public class EdicaoFragment extends Fragment implements View.OnClickListener {

    BDUsuario bdUsuario;
    String idUsuario;
    Usuario u;
    Button btSalvar;
    EditText txt_nome;
    EditText txt_email;
    EditText txt_nome_usuario;
    EditText txt_senha;
    EditText txt_confirmar_senha;

    public EdicaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        idUsuario = ((Home) getActivity()).getUserId();

        return inflater.inflate(R.layout.fragment_edicao_usuario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (idUsuario != null && !idUsuario.equals("")) {
            bdUsuario = BDUsuario.getInstance(getContext());
            u = bdUsuario.findByID(idUsuario);

            btSalvar = view.findViewById(R.id.btn_registrar_edicao);
            txt_nome = view.findViewById(R.id.txt_nome_edicao);
            txt_email = view.findViewById(R.id.txt_email_edicao);
            txt_nome_usuario = view.findViewById(R.id.txt_nome_usuario_edicao);
            txt_senha = view.findViewById(R.id.txt_login_senha_edicao);
            txt_confirmar_senha = view.findViewById(R.id.txt_confirmar_senha_edicao);

            txt_nome.setText(u.getNome());
            txt_email.setText(u.getEmail());
            txt_nome_usuario.setText(u.getUsuario());

            btSalvar.setOnClickListener(this);

        }

    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.btn_registrar_edicao) {
//            Toast.makeText(getContext(), "Estou tentando salvar a edição", Toast.LENGTH_SHORT).show();
////            bdUsuario = BDUsuario.getInstance(getContext());
////
////            u.setNome(txt_nome.getText().toString());
////            u.setEmail(txt_email.getText().toString());
////            u.setUsuario(txt_nome_usuario.getText().toString());
////            u.setSenha(txt_senha.getText().toString());
////
////            bdUsuario.atualizaUsuario(u);
//        }
        switch (v.getId()) {
            case R.id.btn_registrar_edicao:
                String nome = txt_nome.getText().toString();
                String email = txt_email.getText().toString();
                String usuario = txt_nome_usuario.getText().toString();
                String senha = txt_senha.getText().toString();
                String confirmar_senha = txt_confirmar_senha.getText().toString();
                if (!nome.isEmpty() &&
                    !email.isEmpty() &&
                    !usuario.isEmpty()
                ) {

                    if (!senha.equals(confirmar_senha)) {
                        Toast.makeText(getContext(), "As senhas não conferem!", Toast.LENGTH_SHORT).show();
                    } else {
                        bdUsuario = BDUsuario.getInstance(getContext());
                        u.setNome(txt_nome.getText().toString());
                        u.setEmail(txt_email.getText().toString());
                        u.setUsuario(txt_nome_usuario.getText().toString());

                        if (!senha.isEmpty()) {
                            u.setSenha(txt_senha.getText().toString());
                        }

                        bdUsuario.atualizaUsuario(u);
                    }
                } else {
                    Toast.makeText(getContext(), "Erro ao atualizar. " +
                            "\nVerifique os campos e tente novamente", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}