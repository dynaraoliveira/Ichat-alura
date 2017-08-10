package br.com.dynara.ichat_alura.service;

import br.com.dynara.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Dynara on 06/08/2017.
 */

public interface ChatService {

    @POST("polling")
    Call<Void> enviar (@Body Mensagem mensagem);

    @GET("polling")
    Call<Mensagem> receberMensagens();
}
