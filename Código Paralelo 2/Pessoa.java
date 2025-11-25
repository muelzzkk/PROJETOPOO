package Projeto_POO;

public abstract class Pessoa {

    protected final String nome;
    protected final String cpf;
    protected final String email;

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }
}
