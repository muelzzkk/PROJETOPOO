package Projeto_POO.src.test.java;

import Projeto_POO.src.main.java.model.Cliente;
import Projeto_POO.src.main.java.model.Exceptions.TransacaoInvalidaException;
import Projeto_POO.src.main.java.model.Transacao;
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
        assertThrows(TransacaoInvalidaException.class, () -> new Transacao(null, 10));
        assertThrows(TransacaoInvalidaException.class, () -> new Transacao(c, 0));
        assertThrows(TransacaoInvalidaException.class, () -> new Transacao(c, -5));
    }
}
