package Projeto_POO;


public class Main {
    public static void main(String[] args) {
        ProgramaFidelidade programaFidelidade = new ProgramaFidelidade();

        Cliente c1 = new Cliente("Marco", "123", "marco@gmail.com");

        c1.clienteTransacao(25.00);

        programaFidelidade.exibirClientes();
        programaFidelidade.exibirTransacoes();

        Cliente c2 = new Cliente("Samuel", "345", "samuel@gmail.com");

        c2.clienteTransacao(200.00);

        programaFidelidade.exibirClientes();
        programaFidelidade.exibirTransacoes();

        c1.clienteTransacao(25.00);

        programaFidelidade.exibirClientes();
        programaFidelidade.exibirTransacoes();

    }
}

