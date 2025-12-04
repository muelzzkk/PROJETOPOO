package exceptions;

public class ClienteException extends RuntimeException {
    public ClienteException(String mensagem) {
        super(mensagem);
    }
}
