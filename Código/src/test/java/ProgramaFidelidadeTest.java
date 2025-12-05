package Projeto_POO.src.test.java;

import Projeto_POO.src.main.java.model.StatusCliente;
import Projeto_POO.src.main.java.model.Exceptions.ArquivoException;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.FileWriter;
import Projeto_POO.src.main.java.model.Cliente;
import Projeto_POO.src.main.java.model.Exceptions.ClienteJaRegistradoException;
import Projeto_POO.src.main.java.model.ProgramaFidelidade;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramaFidelidadeTest {

    @BeforeEach
    void limparArquivos() {
        new File("clientes.json").delete();
        new File("transacoes.json").delete();
    }

    @Test
    void adicionarCliente() {
        ProgramaFidelidade p = new ProgramaFidelidade();
        Cliente c = new Cliente("Ana", "12345678901", "a@a.com");
        p.adicionarCliente(c);
        assertNotNull(p.buscarClientePorCPF("12345678901"));
    }

    @Test
    void clienteDuplicado() {
        ProgramaFidelidade p = new ProgramaFidelidade();
        Cliente c1 = new Cliente("Ana", "12345678901", "a@a.com");
        Cliente c2 = new Cliente("B", "12345678901", "b@b.com");
        p.adicionarCliente(c1);
        assertThrows(ClienteJaRegistradoException.class, () -> p.adicionarCliente(c2));
    }

    @Test
    void fazerTransacao() {
        ProgramaFidelidade p = new ProgramaFidelidade();
        Cliente c = new Cliente("Ana", "12345678901", "a@a.com");
        p.adicionarCliente(c);
        p.fazerTransacao("12345678901", 200);
        assertTrue(c.getCartaoFidelidade().getTotalPontos() > 0);
    }

    @Test
    void faixaDePontos() {
        ProgramaFidelidade p = new ProgramaFidelidade();
        Cliente c = new Cliente("Ana", "12345678901", "a@a.com");
        p.adicionarCliente(c);

        p.fazerTransacao("12345678901", 5000);
        assertEquals(StatusCliente.PRATA, c.getStatus());

        p.fazerTransacao("12345678901", 10050);
        assertEquals(StatusCliente.OURO, c.getStatus());
    }

    @Test
    void transacaoComCadastroAutomatico() {
        ProgramaFidelidade p = new ProgramaFidelidade();
        p.fazerTransacaoComCadastroAutomatico("Ana", "12345678901", "a@a.com", 200);
        assertNotNull(p.buscarClientePorCPF("12345678901"));
    }

    @Test
    void carregarArquivoComPontosInvalidos() throws Exception {
        FileWriter fw = new FileWriter("clientes.json");
        fw.write("[{\"nome\":\"A\",\"cpf\":\"12345678901\",\"email\":\"a@a.com\",\"pontos\":-1,\"totalPontos\":10,\"status\":\"BRONZE\"}]");
        fw.close();
        assertThrows(ArquivoException.class, ProgramaFidelidade::new);
    }

    @Test
    void arquivoCorrompido() throws Exception {
        FileWriter fw = new FileWriter("clientes.json");
        fw.write("{corrompido]");
        fw.close();
        assertThrows(ArquivoException.class, ProgramaFidelidade::new);
    }
}
