/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.model;

/**
 *
 * @author hiro
 */
public class Result {
    
    private String mensagem;

    public Result() {}

    public Result(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
