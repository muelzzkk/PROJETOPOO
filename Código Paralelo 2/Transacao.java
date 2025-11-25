package Projeto_POO;

import java.util.ArrayList;

public class Transacao {

    private Cliente cliente;
    private double valor;
    private final ProgramaFidelidade programafidelidade = new ProgramaFidelidade();


    public Transacao(String nome, String cpf, String email, double valor) {
        this.cliente = programafidelidade.construirCliente(nome, cpf, email);
        this.valor = valor;
    }

    public void adicionarCliente(Cliente cliente) {
        programafidelidade.adicionarCliente(cliente);
    }


    @Override
    public String toString() {
        return "Transacao{" +
                "cliente=" + cliente +
                ", valor=" + valor +
                '}';
    }
}
