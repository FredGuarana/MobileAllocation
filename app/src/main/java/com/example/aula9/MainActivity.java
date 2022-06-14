package com.example.aula9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText enviarMensagem;
    private CursoService requestCurso;
    private CursoResponse cursoResponse;

    private ArrayAdapter<String> adapter;

    private EditText etNomeCurso;
    private EditText etIdCurso;
    private Button btListarRegistros;
    private Button btSalvaCurso;
    private Button btEditarCurso;
    private Button btDeletar;

    private ListView lvListCurso;
    private List<String> listaNomeCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CursoPost cursoPost = new CursoPost();
//        cursoPost.setName(getPackageName().);

        listaNomeCursos = new ArrayList<>();
        requestCurso = new RetrofitConfig()
                .criarService();

        etNomeCurso = findViewById(R.id.etNomeCurso);
        etIdCurso = findViewById(R.id.etIdCurso);
        btSalvaCurso = findViewById(R.id.btSalvaCurso);
        btEditarCurso = findViewById(R.id.btEditarCurso);
        lvListCurso = findViewById(R.id.lvListCursos);

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1, listaNomeCursos);
        lvListCurso.setAdapter(adapter);
//        alterarCurso();
//        executarRequestDelete();

        enviarMensagem = findViewById(R.id.etNomeCurso);

        btSalvaCurso.setOnClickListener(v -> {
            String conteudo = enviarMensagem.getText().toString();


            CursoPost novoCurso = new CursoPost();
            novoCurso.setName(conteudo);


        requestCurso.createRequestPost(novoCurso).enqueue(new Callback<CursoResponse>() {
            @Override
            public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                cursoResponse = response.body();
                Toast.makeText(getApplicationContext(), "Curso " + conteudo + "Salvo com sucesso", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<CursoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();
            }
        });
    });
    }
    private void alterarCurso() {

        btEditarCurso.setOnClickListener(view -> {
            String editarCurso = enviarMensagem.getText().toString();
            String idDigitado = etIdCurso.getText().toString();
            CursoPost alterarNome = new CursoPost();
            alterarNome.setName(editarCurso);

            int idCurso = Integer.parseInt(idDigitado);

            requestCurso.createRequestPut(alterarNome, idCurso).enqueue(new Callback<CursoResponse>() {
                @Override
                public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                    Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<CursoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_LONG).show();
                }

            });
        });
    }



        public void executarRequestGetAll(View view) {
            requestCurso.createRequestGetAll().enqueue(new Callback<List<CursoResponse>>() {
                @Override
                public void onResponse(Call<List<CursoResponse>> call, Response<List<CursoResponse>> response) {
                    Toast.makeText(getApplicationContext(), "Lista gerada", Toast.LENGTH_LONG).show();

                    List<CursoResponse> cursoLista = response.body();
                    for (CursoResponse curso : cursoLista) {
                        listaNomeCursos.add(curso.getName());
                    }
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<List<CursoResponse>> call, Throwable t){
                    Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_LONG).show();
                    }
                });
            }


        public void executarRequestDelete() {
            btDeletar.setOnClickListener(v -> {

                requestCurso.createRequestDelete(2045).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_LONG).show();
                    }

                });
            });
        }

}