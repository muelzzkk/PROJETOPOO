import exceptions.CartaoFidelidadeException;

public class CartaoFidelidade {

    private int pontos;
    private int totalPontos;

    public CartaoFidelidade() {
        this.pontos = 0;
        this.totalPontos = 0;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        if (pontos < 0) throw new CartaoFidelidadeException("Pontos inválidos (negativo).");
        this.pontos = pontos;
    }

    public int getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(int totalPontos) {
        if (totalPontos < 0) throw new CartaoFidelidadeException("Total de pontos inválido (negativo).");
        this.totalPontos = totalPontos;
    }

    public void adicionarPontos(int pontos) {
        if (pontos <= 0) throw new CartaoFidelidadeException("Quantidade de pontos deve ser positiva.");

        this.pontos += pontos;
        this.totalPontos += pontos;
    }

    public boolean resgatar(int pontos) {
        if (pontos <= 0) throw new CartaoFidelidadeException("Valor para resgate inválido.");

        if (this.pontos < pontos) return false;

        this.pontos -= pontos;
        return true;
    }

    @Override
    public String toString() {
        return "Pontos: " + pontos + " (Total: " + totalPontos + ")";
    }
}