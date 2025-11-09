package Projeto_POO;

public class Transacao {

    private Pessoa cliente;
    private double valor;

    public Transacao(Pessoa cliente, double valor) {
        this.cliente = cliente;
        this.valor = valor;
        adicionarPontos();
    }

    private void adicionarPontos() {
        if (valor / 50 > 1) {
            cliente.setPontos((int) (valor / 50));
        }
    }

    public int exibirPontos() {
        return cliente.getPontos();
    }
}
