package br.com.dynara.ichat_alura.modelo;

/**
 * Created by Dynara on 04/08/2017.
 */

public class Mensagem {

    private String texto;
    private int id;

    public Mensagem(int id, String texto) {
        this.texto = texto;
        this.id = id;
    }

    public String getTexto() {
        return this.texto;
    }

    public int getId() {
        return this.id;
    }
}
