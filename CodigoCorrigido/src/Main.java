public class Main {
    public static void main(String[] args) {

        ProgramaFidelidade pf = new ProgramaFidelidade();

        System.out.println("======= TESTE 1: ADICIONAR CLIENTE MANUAL =======");
        Cliente c1 = new Cliente("Diogo", "11122233344", "diogo@email.com");
        pf.adicionarCliente(c1);

        System.out.println("\n======= TESTE 2: FAZER TRANSAÇÃO =======");
        pf.fazerTransacao("11122233344", 150);  // deve gerar pontos de acordo com categoria atual

        System.out.println("\n======= TESTE 3: FAZER TRANSAÇÃO COM CADASTRO AUTOMÁTICO =======");
        pf.fazerTransacaoComCadastroAutomatico(
                "Maria",
                "99988877766",
                "maria@email.com",
                500
        );

        System.out.println("\n======= TESTE 4: RESGATAR PONTOS =======");
        boolean resgatado = c1.getCartaoFidelidade().resgatar(1);
        System.out.println("Resgate de pontos para Diogo: " + (resgatado ? "OK" : "Falhou"));

        System.out.println("\n======= TESTE 5: EXIBIR CLIENTES =======");
        pf.exibirClientes();

        System.out.println("\n======= TESTE 6: EXIBIR TRANSAÇÕES =======");
        pf.exibirTransacoes();

        System.out.println("\n======= TESTE 7: TESTAR PERSISTÊNCIA (salvar + carregar) =======");
        // Apenas chama operações que já salvam internamente.
        pf.fazerTransacao("11122233344", 50);
        System.out.println("Arquivos JSON devem ter sido salvos formatados.");

        System.out.println("\n======= FIM DOS TESTES =======");
    }
}
