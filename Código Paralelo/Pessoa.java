package Projeto_POO;

public abstract class Pessoa {

    private String nome;
    private String cpf;
    private String email;

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public abstract int getPontos();
    public abstract void setPontos(int pontos);
}
