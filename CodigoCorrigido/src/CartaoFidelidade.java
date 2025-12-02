public class CartaoFidelidade {

    private int pontos;

    public CartaoFidelidade() {
        this.pontos = 0;
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarPontos(int pontos) {
        if (pontos < 0)
            throw new ProgramaFidelidadeException("Quantidade de pontos deve ser positiva.");
        this.pontos += pontos;
    }

    public boolean resgatar(int pontos) {
        if (pontos <= 0)
            throw new ProgramaFidelidadeException("Valor para resgate inválido.");

        if (this.pontos < pontos)
            return false;

        this.pontos -= pontos;
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(pontos);
    }
}
