package br.com.dynara.ichat_alura.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dynara on 10/08/17.
 */

public class EnviarMensagemCallback implements Callback<Void> {

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {

    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
}
