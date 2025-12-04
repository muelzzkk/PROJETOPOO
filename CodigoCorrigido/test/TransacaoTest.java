import exceptions.TransacaoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransacaoTest {

    @Test
    void criarTransacao() {
        Cliente c = new Cliente("Ana", "12345678901", "a@a.com");
        Transacao t = new Transacao(c, 100);
        assertEquals(100, t.getValor());
        assertEquals(c, t.getCliente());
    }

    @Test
    void transacaoInvalida() {
        Cliente c = new Cliente("Ana", "12345678901", "a@a.com");
        assertThrows(TransacaoException.class, () -> new Transacao(null, 10));
        assertThrows(TransacaoException.class, () -> new Transacao(c, 0));
        assertThrows(TransacaoException.class, () -> new Transacao(c, -5));
    }
}
