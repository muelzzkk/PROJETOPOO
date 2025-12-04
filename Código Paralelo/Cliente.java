package Projeto_POO;

public class Cliente extends Pessoa {

    private int pontos;

    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
        this.pontos = 0;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
