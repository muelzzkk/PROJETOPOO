public class Transacao {

    private final Cliente cliente;
    private final double valor;

    public Transacao(Cliente cliente, double valor) {
        if (cliente == null)
            throw new ProgramaFidelidadeException("Cliente não pode ser nulo.");

        if (valor <= 0)
            throw new ProgramaFidelidadeException("Valor da transação deve ser positivo.");

        this.cliente = cliente;
        this.valor = valor;

        int pontosGanhos = (int)(valor / 10);
        cliente.getCartaoFidelidade().adicionarPontos(pontosGanhos);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Cliente=" + cliente.getNome() + ", valor=" + valor;
    }
}