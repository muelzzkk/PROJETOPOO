package Projeto_POO;

import java.util.ArrayList;

public class ProgramaFidelidade {

    ArrayList<Pessoa> clientes = new ArrayList<>();
    ArrayList<Transacao> compras = new ArrayList<>();

    public void adicionarCliente(Pessoa pessoa) {
        clientes.add(pessoa);
    }

    public void adicionarTransacao(Transacao transacao) {
        compras.add(transacao);
    }
}
