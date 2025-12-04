import exceptions.CartaoFidelidadeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartaoFidelidadeTest {

    @Test
    void adicionarPontos() {
        CartaoFidelidade c = new CartaoFidelidade();
        c.adicionarPontos(20);
        assertEquals(20, c.getPontos());
        assertEquals(20, c.getTotalPontos());
    }

    @Test
    void adicionarPontosInvalido() {
        CartaoFidelidade c = new CartaoFidelidade();
        assertThrows(CartaoFidelidadeException.class, () -> c.adicionarPontos(0));
        assertThrows(CartaoFidelidadeException.class, () -> c.adicionarPontos(-1));
    }

    @Test
    void resgatar() {
        CartaoFidelidade c = new CartaoFidelidade();
        c.adicionarPontos(50);
        assertTrue(c.resgatar(20));
        assertEquals(30, c.getPontos());
    }

    @Test
    void resgatarInvalido() {
        CartaoFidelidade c = new CartaoFidelidade();
        assertThrows(CartaoFidelidadeException.class, () -> c.resgatar(0));
        assertThrows(CartaoFidelidadeException.class, () -> c.resgatar(-5));
    }

    @Test
    void resgatarSemPontos() {
        CartaoFidelidade c = new CartaoFidelidade();
        c.adicionarPontos(10);
        assertFalse(c.resgatar(20));
    }

    @Test
    void settersValidam() {
        CartaoFidelidade c = new CartaoFidelidade();
        assertThrows(CartaoFidelidadeException.class, () -> c.setPontos(-1));
        assertThrows(CartaoFidelidadeException.class, () -> c.setTotalPontos(-1));
    }
}
