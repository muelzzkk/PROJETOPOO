package Projeto_POO;

public class Cliente extends Pessoa {

    private final CartaoFidelidade cartaoFidelidade;
    private final ProgramaFidelidade programafidelidade = new ProgramaFidelidade();
    private int pontos;

    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
        this.cartaoFidelidade = new CartaoFidelidade();
        this.pontos += 5;
    }


    public CartaoFidelidade getCartaoFidelidade() {
        return cartaoFidelidade;
    }

    public void clienteTransacao(double valor) {
        Transacao transacao = new Transacao(nome, cpf, email, valor);
        programafidelidade.adicionarTransacao(transacao);
    }

    public boolean resgatarPontos(int pontos) {
        return cartaoFidelidade.resgatar(pontos);
    }

    @Override
    public String toString() {
        return "Cliente: " + super.nome + ", CPF: " + super.cpf + ", Email: " + super.email + ", Cartão Fidelidade: " + cartaoFidelidade + ", Pontos: " + this.pontos;
    }
}
