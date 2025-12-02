import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {

    @Test
    void deveCriarTransacaoEAdicionarPontos() {
        Cliente c = new Cliente("Joao", "123", "a@a.com");

        new Transacao(c, 100); // 100 reais → 10 pontos

        assertEquals(10, c.getCartaoFidelidade().getPontos());
    }

    @Test
    void deveLancarErroSeClienteForNulo() {
        assertThrows(ProgramaFidelidadeException.class, () -> new Transacao(null, 50));
    }

    @Test
    void deveLancarErroSeValorForInvalido() {
        Cliente c = new Cliente("Joao", "123", "a@a.com");

        assertThrows(ProgramaFidelidadeException.class, () -> new Transacao(c, 0));
        assertThrows(ProgramaFidelidadeException.class, () -> new Transacao(c, -20));
    }
}
