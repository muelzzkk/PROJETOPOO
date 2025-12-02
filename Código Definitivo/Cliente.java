package Projeto_POO;

public class Cliente extends Pessoa {

    private double saldo;
    private final CartaoFidelidade cartaoFidelidade;
    private final ProgramaFidelidade programafidelidade = new ProgramaFidelidade();
    private Transacao transacao;

    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
        this.cartaoFidelidade = new CartaoFidelidade();
    }

    public CartaoFidelidade getCartaoFidelidade() {
        return cartaoFidelidade;
    }


    public void calcularPontos(double valor) {
        saldo += valor;
        if (saldo >= 50) {
            saldo = saldo - (cartaoFidelidade.calcularPontos(saldo) * 50);
        }
    }

    @Override
    public String toString() {
        return "Cliente: " + super.nome + ", CPF: " + super.cpf + ", Email: " + super.email + ", ";
    }
}
