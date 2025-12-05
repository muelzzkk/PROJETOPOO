package Projeto_POO.src.main.java.model;


import Projeto_POO.src.main.java.model.Exceptions.*;
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

    private List<Cliente> clientes;
    private List<Transacao> transacoes;

    private static final String CLIENTES_FILE = "clientes.json";
    private static final String TRANSACOES_FILE = "transacoes.json";

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ProgramaFidelidade() {
        clientes = new ArrayList<>();
        transacoes = new ArrayList<>();
        carregarDoArquivo();
    }

    public void adicionarCliente(Cliente cliente) {
        if (cliente == null)
            throw new ClienteNaoEncontradoException("Cliente inválido.");

        if (buscarClientePorCPF(cliente.getCpf()) != null)
            throw new ClienteJaRegistradoException("Já existe um cliente com este CPF.");

        clientes.add(cliente);
        salvarClientes();
    }

    public Cliente buscarClientePorCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) return null;

        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
    
    public void resgatarPontos(String cpf, int pontos) 
            throws ClienteNaoEncontradoException, CartaoFidelidadeException {
        
        
        Cliente cliente = buscarClientePorCPF(cpf);
        
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o CPF informado.");
        }
        
        
        boolean resgatado = cliente.getCartaoFidelidade().resgatar(pontos); 
        
        if (resgatado) {
            
            
            cliente.atualizarStatus();
            
            
            salvarClientes(); 
            
        } else {
            throw new PontosInsuficientesException("Pontos insuficientes para este bônus. Pontos atuais: "
                    + cliente.getCartaoFidelidade().getPontos() + ".");
        }
    }

    public void adicionarTransacao(Transacao transacao) {
        if (transacao == null) throw new TransacaoInvalidaException("Transação inválida.");

        Cliente cliente = transacao.getCliente();
        double valor = transacao.getValor();

        if (valor <= 0) throw new TransacaoInvalidaException("Valor da transação deve ser maior que zero.");

        int totalAntes = cliente.getCartaoFidelidade().getTotalPontos();

        int pontosPor50;
        if (totalAntes > 500) {
            pontosPor50 = 3;
        } else if (totalAntes >= 100) {
            pontosPor50 = 2;
        } else {
            pontosPor50 = 1;
        }

        int unidades50 = (int) (valor / 50);
        int pontosGanhos = unidades50 * pontosPor50;

        if (pontosGanhos > 0) {
            cliente.getCartaoFidelidade().adicionarPontos(pontosGanhos);
            cliente.atualizarStatus();
        }

        transacoes.add(transacao);

        salvarTransacoes();
        salvarClientes();
    }

    public void fazerTransacao(String cpf, double valor) {
        Cliente c = buscarClientePorCPF(cpf);
        if (c == null) throw new ClienteNaoEncontradoException("Cliente não encontrado.");
        if (valor <= 0) throw new ValorCompraInvalidoException("Valor inválido.");

        Transacao t = new Transacao(c, valor);
        adicionarTransacao(t);
    }

    public void fazerTransacaoComCadastroAutomatico(String nome, String cpf, String email, double valor) {
        if (cpf == null || cpf.length() != 11)
            throw new TransacaoInvalidaException("CPF inválido, transação cancelada.");

        Cliente c = buscarClientePorCPF(cpf);

        if (c == null) {
            c = new Cliente(nome, cpf, email);
            adicionarCliente(c);
        }

        Transacao t = new Transacao(c, valor);
        adicionarTransacao(t);
    }

    public void exibirClientes() {
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    public void exibirTransacoes() {
        for (Transacao t : transacoes) {
            System.out.println(t);
        }
    }

    private void salvarClientes() {
        List<ClienteDTO> dtoList = new ArrayList<>();

        for (Cliente c : clientes) {
            dtoList.add(new ClienteDTO(
                    c.getNome(),
                    c.getCpf(),
                    c.getEmail(),
                    c.getCartaoFidelidade().getPontos(),
                    c.getCartaoFidelidade().getTotalPontos(),
                    c.getStatus().name()
            ));
        }

        try (FileWriter writer = new FileWriter(CLIENTES_FILE)) {
            gson.toJson(dtoList, writer);
        } catch (Exception e) {
            throw new ArquivoException("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    private void salvarTransacoes() {
        List<TransacaoDTO> dtoList = new ArrayList<>();

        for (Transacao t : transacoes) {
            dtoList.add(new TransacaoDTO(
                    t.getCliente().getCpf(),
                    t.getValor()
            ));
        }

        try (FileWriter writer = new FileWriter(TRANSACOES_FILE)) {
            gson.toJson(dtoList, writer);
        } catch (Exception e) {
            throw new ArquivoException("Erro ao salvar transações: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        File clientesFile = new File(CLIENTES_FILE);

        if (clientesFile.exists()) {
            try (FileReader reader = new FileReader(clientesFile)) {
                Type listType = new TypeToken<List<ClienteDTO>>() {
                }.getType();
                List<ClienteDTO> dtoList = gson.fromJson(reader, listType);

                clientes = new ArrayList<>();

                if (dtoList != null) {
                    for (ClienteDTO dto : dtoList) {
                        Cliente c = new Cliente(dto.nome, dto.cpf, dto.email);

                        if (dto.pontos < 0 || dto.totalPontos < 0)
                            throw new ArquivoException("Arquivo contém pontos inválidos (negativos).");

                        if (dto.pontos > 0) c.getCartaoFidelidade().setPontos(dto.pontos);
                        if (dto.totalPontos > 0) c.getCartaoFidelidade().setTotalPontos(dto.totalPontos);

                        c.atualizarStatus();

                        clientes.add(c);
                    }
                }

            } catch (Exception e) {
                throw new ArquivoException("Erro ao carregar clientes: " + e.getMessage());
            }
        }

        File transacoesFile = new File(TRANSACOES_FILE);

        if (transacoesFile.exists()) {
            try (FileReader reader = new FileReader(transacoesFile)) {
                Type listType = new TypeToken<List<TransacaoDTO>>() {
                }.getType();
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
                throw new ArquivoException("Erro ao carregar transações: " + e.getMessage());
            }
        }
    }

    private static class ClienteDTO {
        String nome;
        String cpf;
        String email;
        int pontos;
        int totalPontos;
        String status;

        ClienteDTO(String nome, String cpf, String email, int pontos, int totalPontos, String status) {
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
            this.pontos = pontos;
            this.totalPontos = totalPontos;
            this.status = status;
        }
    }

    private static class TransacaoDTO {
        String cpfCliente;
        double valor;

        TransacaoDTO(String cpfCliente, double valor) {
            this.cpfCliente = cpfCliente;
            this.valor = valor;
        }
    }
}
