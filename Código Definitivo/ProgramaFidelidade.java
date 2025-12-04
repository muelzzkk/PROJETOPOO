package Projeto_POO;

import java.util.ArrayList;

public class ProgramaFidelidade {

    private static ArrayList<Transacao> transacoes = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private Cliente cliente;
    private Transacao transacao;
    private boolean verificar;

    public ProgramaFidelidade() {
    }

    public void fazerTransacao(String nome, String cpf, String email, double valor) {
        if (cpf.length() != 11) {
            verificar = true;
            System.out.print("CPF inválido, ");
        } else {
            this.cliente = buscarCliente(nome, cpf, email);
        }
        if (verificar == false) {
            cliente.calcularPontos(valor);
            transacao = new Transacao(cliente, valor);
            adicionarTransacao(transacao);
        } else {
            System.out.println("Transação não será feita.");
        }
    }

    public void fazerTransacao(String cpf, double valor) {
       this.cliente = buscarCliente(cpf);
        if (verificar == true) {
            cliente.calcularPontos(valor);
            transacao = new Transacao(cliente, valor);
            adicionarTransacao(transacao);
        } else {
            System.out.println("Cliente não encontrado, transação cancelada");
        }
    }

    public void cadastrarCliente(String nome, String cpf, String email) {
        if (cpf.length() != 11) {
            System.out.println("CPF inválido, cliente não será cadastrado");
        } else {
            this.cliente = buscarCliente(nome, cpf, email);
            if (verificar == false) {
                this.clientes.add(cliente);
            }
        }
    }


    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void exibirClientes() {
        for (Cliente c : this.clientes) {
            System.out.print(c);
            System.out.println(c.getCartaoFidelidade());
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

    public Cliente buscarCliente(String nome, String cpf, String email) {
        for (Cliente i : this.clientes) {
            if (cpf == i.cpf) {
                System.out.println("Cliente já existe");
                verificar = true;
                return i;
            }
        }
        verificar = false;
        return new Cliente(nome, cpf, email);
    }

    public Cliente buscarCliente(String cpf) {
        for (Cliente i : this.clientes) {
            if (cpf == i.cpf) {
                verificar = true;
                return i;
            }
        }
        verificar = false;
        return null;
    }

    public boolean verificarCliente(String cpf) {
        for (Cliente c : this.clientes) {
            if (cpf == c.cpf) {
                cliente = c;
                return false;
            }
        }
        return true;
    }
}
