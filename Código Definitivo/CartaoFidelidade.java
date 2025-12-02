package Projeto_POO;

public class CartaoFidelidade {

    private int pontos;

    public CartaoFidelidade() {
        this.pontos += 0;
    }

    public int getPontos() {
        return pontos;
    }

    public int calcularPontos(double saldo) {
        pontos += (int) (saldo / 50);
        return (int) saldo / 50;
    }

    public void adicionarPontos(int pontos) {
        if (pontos <= 0) return;
        this.pontos += pontos;
    }

    public boolean resgatar(int pontos) {
        if (pontos <= 0) return false;
        if (this.pontos >= pontos) {
            this.pontos -= pontos;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pontos: " + pontos;
    }

}

