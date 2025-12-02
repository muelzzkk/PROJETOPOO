import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteCorretamente() {
        Cliente c = new Cliente("Joao", "123", "email@test.com");

        assertEquals("Joao", c.getNome());
        assertEquals("123", c.getCpf());
        assertEquals("email@test.com", c.getEmail());
        assertNotNull(c.getCartaoFidelidade());
    }

    @Test
    void deveLancarErroParaNomeInvalido() {
        assertThrows(ProgramaFidelidadeException.class,
                () -> new Cliente("", "123", "a@a.com"));
    }

    @Test
    void deveLancarErroParaCPFInvalido() {
        assertThrows(ProgramaFidelidadeException.class,
                () -> new Cliente("Joao", "", "a@a.com"));
    }

    @Test
    void deveLancarErroParaEmailInvalido() {
        assertThrows(ProgramaFidelidadeException.class,
                () -> new Cliente("Joao", "123", "emailSemArroba"));
    }

    @Test
    void deveResgatarPontosDoCartao() {
        Cliente c = new Cliente("Joao", "123", "a@a.com");
        c.getCartaoFidelidade().adicionarPontos(100);

        assertTrue(c.resgatarPontos(50));
        assertEquals(50, c.getCartaoFidelidade().getPontos());
    }
}
