/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hiro
 */
public class Lancamento implements Serializable {
    
    private int id;
    private String nome;
    private Date data;
    private Double valor;
    private int tipo;
    
    public Lancamento() {
        super();
    }
    
    public Lancamento(int id, String nome, Date data, Double valor, int tipo) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Lancamento(String nome, Date data, Double valor, int tipo) {
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Object data) throws ParseException {
        if (data instanceof String) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            this.data = sdf.parse((String) data);
        } else if (data instanceof Date) 
            this.data = (Date) data;
        
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Lancamentos{" + "id=" + id + ", nome=" + nome + ", data=" + data + ", valor=" + valor + ", tipo=" + tipo + '}';
    }
}
