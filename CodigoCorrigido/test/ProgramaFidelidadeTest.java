import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ProgramaFidelidadeTest {

    private ProgramaFidelidade programa;

    @BeforeEach
    void setup() {
        new File("clientes.json").delete();
        new File("transacoes.json").delete();
        programa = new ProgramaFidelidade();
    }

    @Test
    void deveAdicionarClienteComSucesso() {
        Cliente c = new Cliente("Ana", "111", "a@a.com");

        assertTrue(programa.adicionarCliente(c));
        assertNotNull(programa.buscarClientePorCPF("111"));
    }

    @Test
    void naoDeveAdicionarClienteDuplicado() {
        Cliente c1 = new Cliente("Ana", "111", "a@a.com");
        Cliente c2 = new Cliente("Ana", "111", "a@a.com");

        programa.adicionarCliente(c1);

        assertThrows(ProgramaFidelidadeException.class,
                () -> programa.adicionarCliente(c2));
    }

    @Test
    void deveAdicionarTransacao() {
        Cliente c = new Cliente("Ana", "111", "a@a.com");
        programa.adicionarCliente(c);

        Transacao t = new Transacao(c, 100);

        assertDoesNotThrow(() -> programa.adicionarTransacao(t));
    }

    @Test
    void deveBuscarClientePorCPF() {
        Cliente c = new Cliente("Ana", "111", "a@a.com");
        programa.adicionarCliente(c);

        Cliente encontrado = programa.buscarClientePorCPF("111");

        assertNotNull(encontrado);
        assertEquals("111", encontrado.getCpf());
    }

    @Test
    void deveRetornarNullSeClienteNaoExistir() {
        assertNull(programa.buscarClientePorCPF("999"));
    }
}
