package br.com.dynara.ichat_alura.service;

import org.json.JSONStringer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.dynara.ichat_alura.modelo.Mensagem;

/**
 * Created by Dynara on 06/08/2017.
 */

public class ChatService {
    public void enviar (final Mensagem mensagem){

        new Thread(new Runnable() {
            @Override
            public void run() {
                String texto = mensagem.getTexto();
                try {
                    HttpURLConnection httpConnection = (HttpURLConnection) new URL("http://192.168.1.117:8080/polling").openConnection();
                    httpConnection.setRequestMethod("POST");
                    httpConnection.setRequestProperty("context-type", "application/json");

                    JSONStringer json = new JSONStringer()
                            .object()
                            .key("text")
                            .value(texto)
                            .key("id")
                            .value(mensagem.getId())
                            .endObject();

                    OutputStream outputStream = httpConnection.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);

                    printStream.println(json.toString());

                    httpConnection.connect();
                    httpConnection.getInputStream();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
