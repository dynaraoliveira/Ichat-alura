package br.com.dynara.ichat_alura.callback;

import br.com.dynara.ichat_alura.activity.MainActivity;
import br.com.dynara.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dynara on 10/08/17.
 */

public class OuvirMensagensCallback implements Callback<Mensagem> {
    private MainActivity activity;

    public OuvirMensagensCallback(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if (response.isSuccessful()){
            Mensagem mensagem = response.body();
            this.activity.colocaNaLista(mensagem);
            this.activity.ouvirMensagens();
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        this.activity.ouvirMensagens();
    }
}
