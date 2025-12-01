import com.google.gson
        .Gson;
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
        for(Cliente c : clientes){
            if(c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    public boolean adicionarCliente(Cliente c){
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equals(c.getCpf()) || cliente.getEmail().equals(c.getEmail())){
                System.out.println("Cliente já existe: " + c.getCpf() + " / " + c.getEmail());
                return false;
            }
        }
        clientes.add(c);
        salvarClientes();
        return true;
    }

    public void adicionarTransacao(Transacao t){
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
            dtoList.add(new ClienteDTO(c.getNome(), c.getCpf(), c.getEmail(), c.getCartaoFidelidade().getPontos()));
        }
        try(FileWriter writer = new FileWriter(CLIENTES_FILE)){
            gson.toJson(dtoList, writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void salvarTransacoes(){
        List<TransacaoDTO> dtoList = new ArrayList<>();
        for(Transacao t : transacoes){
            dtoList.add(new TransacaoDTO(t.getCliente().getCpf(), t.getValor()));
        }
        try(FileWriter writer = new FileWriter(TRANSACOES_FILE)){
            gson.toJson(dtoList, writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void carregarDoArquivo(){
        File clientesFile = new File(CLIENTES_FILE);
        File transacoesFile = new File(TRANSACOES_FILE);

        if(clientesFile.exists()){
            try(FileReader reader = new FileReader(clientesFile)){
                Type listType = new TypeToken<List<ClienteDTO>>(){}.getType();
                List<ClienteDTO> dtoList = gson.fromJson(reader, listType);
                clientes = new ArrayList<>();
                for(ClienteDTO dto : dtoList){
                    Cliente c = new Cliente(dto.nome, dto.cpf, dto.email);
                    c.getCartaoFidelidade().adicionarPontos(dto.pontos);
                    clientes.add(c);
                }
            }catch(Exception e){ e.printStackTrace(); }
        }

        if(transacoesFile.exists()){
            try(FileReader reader = new FileReader(transacoesFile)){
                Type listType = new TypeToken<List<TransacaoDTO>>(){}.getType();
                List<TransacaoDTO> dtoList = gson.fromJson(reader, listType);
                transacoes = new ArrayList<>();
                for(TransacaoDTO dto : dtoList){
                    Cliente c = buscarClientePorCPF(dto.cpfCliente);
                    if(c != null){
                        transacoes.add(new Transacao(c, dto.valor));
                    }
                }
            }catch(Exception e){ e.printStackTrace(); }
        }
    }
}
