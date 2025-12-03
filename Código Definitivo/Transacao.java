package Projeto_POO;

import java.util.ArrayList;

public class Transacao {

    private Cliente cliente;
    private double valor;
    private final ProgramaFidelidade programafidelidade = new ProgramaFidelidade();


    public Transacao(Cliente cliente, double valor) {
        this.cliente = cliente;
        this.valor = valor;
        if (programafidelidade.verificarCliente(cliente.cpf) == true) {
            adicionarCliente(cliente);
        }
    }

    public void adicionarCliente(Cliente cliente) {
        programafidelidade.adicionarCliente(cliente);
    }

    @Override
    public String toString() {
        return "Transação = " + cliente + "Valor: R$" + valor;
    }
}
