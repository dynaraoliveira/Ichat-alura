package br.com.dynara.ichat_alura.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.dynara.ichat_alura.R;
import br.com.dynara.ichat_alura.modelo.Mensagem;

/**
 * Created by Dynara on 04/08/2017.
 */

public class MensagemAdapter extends BaseAdapter {
    private List<Mensagem> mensagens;
    private Context context;
    private int idDoCliente;

    public MensagemAdapter(List<Mensagem> mensagens, Context context, int idDoCliente) {
        this.mensagens = mensagens;
        this.context = context;
        this.idDoCliente = idDoCliente;
    }

    @Override
    public int getCount() {
        return this.mensagens.size();
    }

    @Override
    public Mensagem getItem(int position) {
        return this.mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.mensagem, parent, false);
        }
        TextView texto = (TextView) view.findViewById(R.id.texto);

        Mensagem mensagem = getItem(position);

        if (this.idDoCliente != mensagem.getId()) {
            texto.setBackgroundColor(Color.CYAN);
        }

        texto.setText(mensagem.getTexto());

        return view;
    }
}
