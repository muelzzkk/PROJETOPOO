package Projeto_POO;

import java.util.ArrayList;

public class ProgramaFidelidade {

    private static ArrayList<Transacao> transacoes = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private Cliente cliente;

    public ProgramaFidelidade() {
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void exibirClientes() {
        for (Cliente c : this.clientes) {
            System.out.println(c);
        }
    }

    public void adicionarTransacao(Transacao transacao) {
        this.transacoes.add(transacao);
    }

    public void exibirTransacoes() {
        for (Transacao t : this.transacoes) {
            System.out.println(t);
        }
    }

    public Cliente buscarCliente(String nome) {
        for (Cliente c : this.clientes) {
            if (nome.equals(c.nome)) {
                return c;
            }
        }
        return null;
    }

    public Cliente construirCliente(String nome, String cpf, String email) {
        return new Cliente(nome, cpf, email);
    }
}
