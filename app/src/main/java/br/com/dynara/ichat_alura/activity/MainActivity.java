package br.com.dynara.ichat_alura.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.dynara.ichat_alura.R;
import br.com.dynara.ichat_alura.adapter.MensagemAdapter;
import br.com.dynara.ichat_alura.callback.EnviarMensagemCallback;
import br.com.dynara.ichat_alura.callback.OuvirMensagensCallback;
import br.com.dynara.ichat_alura.modelo.Mensagem;
import br.com.dynara.ichat_alura.service.ChatService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listaDeMensagens;
    private List<Mensagem> mensagens;
    private ChatService chatService;

    private int idDoCliente = 1;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDeMensagens = (ListView) findViewById(R.id.main_lista);
        mensagens = new ArrayList<>();
        MensagemAdapter mensagemAdapter = new MensagemAdapter(mensagens, this, idDoCliente);
        listaDeMensagens.setAdapter(mensagemAdapter);

        editText = (EditText) findViewById(R.id.main_texto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.85:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        chatService = retrofit.create(ChatService.class);

        ouvirMensagens();

        button = (Button) findViewById(R.id.main_botao);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString())).enqueue(new EnviarMensagemCallback());
            }
        });

    }

    public void colocaNaLista(Mensagem mensagem) {
        mensagens.add(mensagem);

        MensagemAdapter mensagemAdapter = new MensagemAdapter(mensagens, this, idDoCliente);
        listaDeMensagens.setAdapter(mensagemAdapter);
        chatService.receberMensagens();
    }

    public void ouvirMensagens() {
        Call<Mensagem> call = chatService.receberMensagens();
        call.enqueue(new OuvirMensagensCallback(this));
    }
}
