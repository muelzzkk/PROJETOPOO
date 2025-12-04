package exceptions;

public class CartaoFidelidadeException extends RuntimeException {
    public CartaoFidelidadeException(String mensagem) {
        super(mensagem);
    }
}
