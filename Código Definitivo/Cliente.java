package Projeto_POO;

public class Cliente extends Pessoa {

    private double saldo;
    private final CartaoFidelidade cartaoFidelidade;
    private final ProgramaFidelidade programafidelidade = new ProgramaFidelidade();
    private int valor_de_negocio = 50;
    private StatusCliente status;

    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
        this.cartaoFidelidade = new CartaoFidelidade();
        this.status = StatusCliente.BRONZE;
    }

    public CartaoFidelidade getCartaoFidelidade() {
        return cartaoFidelidade;
    }


    public void calcularPontos(double valor) {
        saldo += valor;
        if (saldo >= valor_de_negocio) {
            saldo = saldo - (cartaoFidelidade.calcularPontos(saldo) * valor_de_negocio);
        }
        analisarStatus();
    }

    public void analisarStatus() {
        if (cartaoFidelidade.getTotalPontos() >= 50) {
            status = StatusCliente.PRATA;
        } else if (cartaoFidelidade.getTotalPontos() >= 100) {
            status = StatusCliente.OURO;
        } else {
            return;
        }
    }

    @Override
    public String toString() {
        return "Cliente: " + super.nome + ", CPF: " + super.cpf + ", Email: " + super.email + ", Status: " + this.status + ", ";
    }
}
