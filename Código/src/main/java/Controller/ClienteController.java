/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto_POO.src.main.java.Controller;

import Projeto_POO.src.main.java.model.Cliente;
import Projeto_POO.src.main.java.model.ProgramaFidelidade;
import Projeto_POO.src.main.java.model.Exceptions.CampoInvalidoException;
import Projeto_POO.src.main.java.model.Exceptions.DadosObrigatoriosException;
import Projeto_POO.src.main.java.model.Exceptions.EmailInvalidoException;
import Projeto_POO.src.main.java.model.Exceptions.ClienteJaRegistradoException;
import Projeto_POO.src.main.java.model.Exceptions.ClienteNaoEncontradoException;

public class ClienteController {
    
    private final ProgramaFidelidade programaFidelidade; 
    
    public ClienteController(ProgramaFidelidade programaFidelidade) { 
        this.programaFidelidade = programaFidelidade;
    }
    
    public void cadastrar(String nome, String cpf, String email) 
            throws DadosObrigatoriosException, EmailInvalidoException, CampoInvalidoException, 
                   ClienteJaRegistradoException, ClienteNaoEncontradoException {
        
        
        if (nome == null || nome.isBlank()|| 
            email == null || email.isBlank()||
            cpf == null || cpf.isBlank()){
            
            throw new DadosObrigatoriosException("Todos os campos são obrigatórios.");
        }
     
        
        if (!email.contains("@") || !email.contains(".") ) {
            throw new EmailInvalidoException("Email inválido!");
        }
        
        
        if (cpf.length() != 11 || !cpf.chars().allMatch(Character::isDigit)) {
            throw new CampoInvalidoException("CPF deve conter 11 dígitos numéricos.");
        }
        
        
        Cliente cliente = new Cliente(nome, cpf, email);   
        programaFidelidade.adicionarCliente(cliente); 
    }
    
    
    public Cliente buscarCliente(String cpf) {
        return programaFidelidade.buscarClientePorCPF(cpf);
    }
}