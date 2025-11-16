package Projeto_POO.Exceptions;

public class TransacaoInvalidaException extends RuntimeException {
    public TransacaoInvalidaException(String message) {
        super(message);
    }
}
