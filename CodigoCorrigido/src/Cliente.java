import exceptions.ClienteException;

public class Cliente extends Pessoa {

    private final CartaoFidelidade cartaoFidelidade;
    private StatusCliente status;

    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);

        if (nome == null || nome.isBlank())
            throw new ClienteException("Nome inválido.");

        if (cpf == null || cpf.isBlank())
            throw new ClienteException("CPF inválido.");

        if (cpf.length() != 11 || !cpf.chars().allMatch(Character::isDigit))
            throw new ClienteException("CPF deve conter 11 dígitos numéricos.");

        if (email == null || email.isBlank() || !email.contains("@"))
            throw new ClienteException("Email inválido.");

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
        if (status == null) throw new ClienteException("Status inválido.");
        this.status = status;
    }

    public void atualizarStatus() {
        int total = cartaoFidelidade.getTotalPontos();

        // Regras pedidas:
        // BRONZE: total < 100
        // PRATA: 100 <= total <= 500
        // OURO: total > 500
        if (total > 500) {
            status = StatusCliente.OURO;
        } else if (total >= 100) {
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
