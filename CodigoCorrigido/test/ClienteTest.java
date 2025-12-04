import exceptions.ClienteException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    void criarCliente() {
        Cliente c = new Cliente("Ana", "12345678901", "a@a.com");
        assertEquals("Ana", c.getNome());
        assertEquals(StatusCliente.BRONZE, c.getStatus());
    }

    @Test
    void criarClienteInvalido() {
        assertThrows(ClienteException.class, () -> new Cliente("", "12345678901", "a@a.com"));
        assertThrows(ClienteException.class, () -> new Cliente("Ana", "123", "a@a.com"));
        assertThrows(ClienteException.class, () -> new Cliente("Ana", "12345678901", "aaa"));
    }

    @Test
    void atualizarStatus() {
        Cliente c = new Cliente("Ana", "12345678901", "a@a.com");
        c.getCartaoFidelidade().adicionarPontos(150);
        c.atualizarStatus();
        assertEquals(StatusCliente.PRATA, c.getStatus());
        c.getCartaoFidelidade().adicionarPontos(400);
        c.atualizarStatus();
        assertEquals(StatusCliente.OURO, c.getStatus());
    }

    @Test
    void setStatus() {
        Cliente c = new Cliente("Ana", "12345678901", "a@a.com");
        assertThrows(ClienteException.class, () -> c.setStatus(null));
    }
}
