package Projeto_POO.src.main.java.model;


import Projeto_POO.src.main.java.model.Exceptions.StatusInvalidoException;



public class Cliente extends Pessoa {

    private final CartaoFidelidade cartaoFidelidade;
    private StatusCliente status;

    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
        this.cartaoFidelidade = new CartaoFidelidade();
        this.status = StatusCliente.BRONZE;
    }

    public CartaoFidelidade getCartaoFidelidade() {
        return cartaoFidelidade;
    }

    public StatusCliente getStatus() {
        return status;
    }

    public void setStatus(StatusCliente status) {
        if (status == null) throw new StatusInvalidoException("Status inválido.");
        this.status = status;
    }

    public void atualizarStatus() {
        int total = cartaoFidelidade.getTotalPontos();

        if (total > 50) {
            status = StatusCliente.OURO;
        } else if (total >= 25) {
            status = StatusCliente.PRATA;
        } else {
            status = StatusCliente.BRONZE;
        }
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CPF: " + cpf + ", Email: " + email + ", Status: " + this.status + ", " + cartaoFidelidade;
    }
}
