/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto_POO.src.main.java.model.Exceptions;

/**
 *
 * @author Samuel
 */
public class TransacaoInvalidaException extends RuntimeException {
    public TransacaoInvalidaException(String message) {
        super(message);
    }
}
