/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto_POO.src.main.java.Controller;

import Projeto_POO.src.main.java.model.ProgramaFidelidade;
import Projeto_POO.src.main.java.model.Exceptions.ClienteNaoEncontradoException;
import Projeto_POO.src.main.java.model.Exceptions.ValorCompraInvalidoException;
import Projeto_POO.src.main.java.model.Exceptions.TransacaoInvalidaException;
import Projeto_POO.src.main.java.model.Exceptions.CartaoFidelidadeException;

public class TransacaoController {

    private final ProgramaFidelidade programa;

    public TransacaoController(ProgramaFidelidade programa) {
        this.programa = programa;
    }

    
    public void registrarCompra(String cpf, String valorStr) 
            throws ClienteNaoEncontradoException, ValorCompraInvalidoException, TransacaoInvalidaException {
        
        if (cpf == null || cpf.isBlank() || valorStr == null || valorStr.isBlank()) {
             throw new ValorCompraInvalidoException("CPF e Valor da Compra são obrigatórios.");
        }

        try {
            double valor = Double.parseDouble(valorStr.replace(",", ".")); 
            programa.fazerTransacao(cpf, valor); 
        } catch (NumberFormatException e) {
            throw new ValorCompraInvalidoException("Valor da compra deve ser um número válido.");
        }
    }
    
    
    public void resgatarBonus(String cpf, int pontosResgate)throws ClienteNaoEncontradoException, CartaoFidelidadeException {
        
        if (cpf == null || cpf.length() != 11) {
            throw new ClienteNaoEncontradoException("CPF inválido ou não informado.");
        }
        
       
        programa.resgatarPontos(cpf, pontosResgate);
    }
}