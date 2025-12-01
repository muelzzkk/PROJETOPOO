public class Main {
    public static void main(String[] args) {
        ProgramaFidelidade programa = new ProgramaFidelidade();

        Cliente c1 = new Cliente("Marco", "123", "marco@gmail.com");
        programa.adicionarCliente(c1);

        Cliente c2 = new Cliente("Samuel", "345", "samuel@gmail.com");
        programa.adicionarCliente(c2);

        programa.adicionarTransacao(new Transacao(c1, 25.00));
        programa.adicionarTransacao(new Transacao(c2, 200.00));
        programa.adicionarTransacao(new Transacao(c1, 25.00));

        System.out.println("\nClientes:");
        programa.exibirClientes();

        System.out.println("\nTransações:");
        programa.exibirTransacoes();
    }
}
