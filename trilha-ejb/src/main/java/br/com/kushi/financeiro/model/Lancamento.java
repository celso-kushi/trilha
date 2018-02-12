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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hiro
 */
@Entity
public class Lancamento implements Serializable {
    
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private int id;
    
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    private Date data;
    private Double valor;
    private int tipo;
    
    public Lancamento() {
        super();
    }
    
    public Lancamento(int id, String descricao, Date data, Double valor, int tipo) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Lancamento(String descricao, Date data, Double valor, int tipo) {
        this.descricao = descricao;
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
        return descricao;
    }

    public void setNome(String nome) {
        this.descricao = nome;
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
        return "Lancamentos{" + "id=" + id + ", descricao=" + descricao + ", data=" + data + ", valor=" + valor + ", tipo=" + tipo + '}';
    }

	/**
	 * @return the lancamento
	 */
	public String getLancamento() {
		return descricao;
	}

	/**
	 * @param lancamento the lancamento to set
	 */
	public void setLancamento(String lancamento) {
		this.descricao = lancamento;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
