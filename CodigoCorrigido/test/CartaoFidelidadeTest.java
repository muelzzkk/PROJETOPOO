import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartaoFidelidadeTest {

    @Test
    void deveAdicionarPontosCorretamente() {
        CartaoFidelidade c = new CartaoFidelidade();
        c.adicionarPontos(50);
        assertEquals(50, c.getPontos());
    }

    @Test
    void deveLancarExcecaoParaAdicionarPontosInvalidos() {
        CartaoFidelidade c = new CartaoFidelidade();
        assertThrows(ProgramaFidelidadeException.class, () -> c.adicionarPontos(-1));
        assertThrows(ProgramaFidelidadeException.class, () -> c.adicionarPontos(-10));
    }

    @Test
    void deveResgatarPontosComSucesso() {
        CartaoFidelidade c = new CartaoFidelidade();
        c.adicionarPontos(100);
        assertTrue(c.resgatar(50));
        assertEquals(50, c.getPontos());
    }

    @Test
    void deveRetornarFalseSeNaoTiverPontosSuficientes() {
        CartaoFidelidade c = new CartaoFidelidade();
        c.adicionarPontos(30);
        assertFalse(c.resgatar(100));
        assertEquals(30, c.getPontos());
    }

    @Test
    void deveLancarExcecaoParaResgatarValorInvalido() {
        CartaoFidelidade c = new CartaoFidelidade();
        assertThrows(ProgramaFidelidadeException.class, () -> c.resgatar(0));
        assertThrows(ProgramaFidelidadeException.class, () -> c.resgatar(-5));
    }
}
