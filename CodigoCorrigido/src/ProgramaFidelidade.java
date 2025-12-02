import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProgramaFidelidade {

    private static final String CLIENTES_FILE = "clientes.json";
    private static final String TRANSACOES_FILE = "transacoes.json";

    private List<Cliente> clientes;
    private List<Transacao> transacoes;
    private final Gson gson;

    public ProgramaFidelidade() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        clientes = new ArrayList<>();
        transacoes = new ArrayList<>();
        carregarDoArquivo();
    }

    private static class ClienteDTO {
        String nome, cpf, email;
        int pontos;

        ClienteDTO(String nome, String cpf, String email, int pontos){
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
            this.pontos = pontos;
        }
    }

    private static class TransacaoDTO {
        String cpfCliente;
        double valor;

        TransacaoDTO(String cpfCliente, double valor){
            this.cpfCliente = cpfCliente;
            this.valor = valor;
        }
    }

    public Cliente buscarClientePorCPF(String cpf){
        if (cpf == null || cpf.isBlank())
            throw new ProgramaFidelidadeException("CPF inválido para busca.");

        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public boolean adicionarCliente(Cliente c){
        if (c == null)
            throw new ProgramaFidelidadeException("Cliente não pode ser nulo.");

        boolean existe = clientes.stream().anyMatch(cliente ->
                cliente.getCpf().equals(c.getCpf()) ||
                        cliente.getEmail().equals(c.getEmail())
        );

        if (existe)
            throw new ProgramaFidelidadeException("Cliente já existe: " + c.getCpf());

        clientes.add(c);
        salvarClientes();
        return true;
    }

    public void adicionarTransacao(Transacao t){
        if (t == null)
            throw new ProgramaFidelidadeException("Transação não pode ser nula.");

        if (t.getValor() <= 0)
            throw new ProgramaFidelidadeException("Valor da transação deve ser maior que zero.");

        transacoes.add(t);
        salvarTransacoes();
        salvarClientes();
    }

    public void exibirClientes(){
        for(Cliente c : clientes){
            System.out.println(c);
        }
    }

    public void exibirTransacoes(){
        for(Transacao t : transacoes){
            System.out.println(t);
        }
    }

    private void salvarClientes(){
        List<ClienteDTO> dtoList = new ArrayList<>();

        for(Cliente c : clientes){
            dtoList.add(new ClienteDTO(
                    c.getNome(),
                    c.getCpf(),
                    c.getEmail(),
                    c.getCartaoFidelidade().getPontos()
            ));
        }

        try (FileWriter writer = new FileWriter(CLIENTES_FILE)) {
            gson.toJson(dtoList, writer);
        } catch (Exception e) {
            throw new ProgramaFidelidadeException("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    private void salvarTransacoes(){
        List<TransacaoDTO> dtoList = new ArrayList<>();

        for(Transacao t : transacoes){
            dtoList.add(new TransacaoDTO(
                    t.getCliente().getCpf(),
                    t.getValor()
            ));
        }

        try (FileWriter writer = new FileWriter(TRANSACOES_FILE)) {
            gson.toJson(dtoList, writer);
        } catch (Exception e) {
            throw new ProgramaFidelidadeException("Erro ao salvar transações: " + e.getMessage());
        }
    }

    private void carregarDoArquivo(){

        File clientesFile = new File(CLIENTES_FILE);

        if (clientesFile.exists()) {
            try (FileReader reader = new FileReader(clientesFile)) {

                Type listType = new TypeToken<List<ClienteDTO>>(){}.getType();
                List<ClienteDTO> dtoList = gson.fromJson(reader, listType);

                clientes = new ArrayList<>();

                if (dtoList != null) {
                    for (ClienteDTO dto : dtoList) {
                        Cliente c = new Cliente(dto.nome, dto.cpf, dto.email);

                        if (dto.pontos < 0)
                            throw new ProgramaFidelidadeException("Arquivo contém pontos inválidos (negativos).");

                        c.getCartaoFidelidade().adicionarPontos(dto.pontos);
                        clientes.add(c);
                    }
                }

            } catch (Exception e) {
                throw new ProgramaFidelidadeException("Erro ao carregar clientes: " + e.getMessage());
            }
        }

        File transacoesFile = new File(TRANSACOES_FILE);

        if (transacoesFile.exists()) {
            try (FileReader reader = new FileReader(transacoesFile)) {

                Type listType = new TypeToken<List<TransacaoDTO>>(){}.getType();
                List<TransacaoDTO> dtoList = gson.fromJson(reader, listType);

                transacoes = new ArrayList<>();

                if (dtoList != null) {
                    for (TransacaoDTO dto : dtoList) {
                        Cliente c = buscarClientePorCPF(dto.cpfCliente);

                        if (c != null) {
                            transacoes.add(new Transacao(c, dto.valor));
                        }
                    }
                }

            } catch (Exception e) {
                throw new ProgramaFidelidadeException("Erro ao carregar transações: " + e.getMessage());
            }
        }
    }
}
