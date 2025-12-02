public class Cliente extends Pessoa {

    private final CartaoFidelidade cartaoFidelidade;

    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);

        if (nome == null || nome.isBlank())
            throw new ProgramaFidelidadeException("Nome inválido.");

        if (cpf == null || cpf.isBlank())
            throw new ProgramaFidelidadeException("CPF inválido.");

        if (email == null || !email.contains("@"))
            throw new ProgramaFidelidadeException("Email inválido.");

        this.cartaoFidelidade = new CartaoFidelidade();
    }

    public CartaoFidelidade getCartaoFidelidade() {
        return cartaoFidelidade;
    }

    public boolean resgatarPontos(int pontos) {
        return cartaoFidelidade.resgatar(pontos);
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CPF: " + cpf + ", Email: " + email + ", Pontos: " + cartaoFidelidade;
    }
}
