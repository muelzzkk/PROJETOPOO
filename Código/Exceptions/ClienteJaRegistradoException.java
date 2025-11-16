package Projeto_POO.Exceptions;

public class ClienteJaRegistradoException extends RuntimeException {
    public ClienteJaRegistradoException(String message) {
        super(message);
    }
}
