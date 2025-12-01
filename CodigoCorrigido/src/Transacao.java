public class Transacao {

    private final Cliente cliente;
    private final double valor;

    public Transacao(Cliente cliente, double valor) {
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
        return "Transacao{" +
                "cliente=" + cliente.getNome() +
                ", valor=" + valor +
                ", pontos ganhos=" + (int)(valor/10) +
                '}';
    }
}
