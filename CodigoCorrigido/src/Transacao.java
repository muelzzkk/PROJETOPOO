import exceptions.TransacaoException;

public class Transacao {

    private final Cliente cliente;
    private final double valor;

    public Transacao(Cliente cliente, double valor) {
        if (cliente == null)
            throw new TransacaoException("Cliente não pode ser nulo.");

        if (valor <= 0)
            throw new TransacaoException("Valor da transação deve ser positivo.");

        this.cliente = cliente;
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Transação = Cliente=" + cliente.getNome() + ", CPF=" + cliente.getCpf() + ", Valor: R$" + valor;
    }
}
