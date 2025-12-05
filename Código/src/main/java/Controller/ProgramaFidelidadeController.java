/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto_POO.src.main.java.Controller;

import Projeto_POO.src.main.java.model.Cliente;
import Projeto_POO.src.main.java.model.ProgramaFidelidade;
import Projeto_POO.src.main.java.model.Exceptions.ClienteNaoEncontradoException;
 


public class ProgramaFidelidadeController {

    private final ProgramaFidelidade programaFidelidade;
    private final ClienteController clienteController;
    private final TransacaoController transacaoController;

    public ProgramaFidelidadeController() {
        this.programaFidelidade = new ProgramaFidelidade();
        this.clienteController = new ClienteController(this.programaFidelidade);
        this.transacaoController = new TransacaoController(this.programaFidelidade);
    }

    public ClienteController getClienteController() {
        return clienteController;
    }

    public TransacaoController getTransacaoController() {
        return transacaoController;
    }
    
   
    public int buscarPontos(String cpf) throws ClienteNaoEncontradoException {
        
        if (cpf == null || cpf.length() != 11 || !cpf.chars().allMatch(Character::isDigit)) {
            throw new ClienteNaoEncontradoException("CPF inválido ou não informado.");
        }
        
        Cliente cliente = programaFidelidade.buscarClientePorCPF(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado.");
        }
        return cliente.getCartaoFidelidade().getPontos();
    }
}