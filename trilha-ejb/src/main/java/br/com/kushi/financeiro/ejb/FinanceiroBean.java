/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.ejb;

import br.com.kushi.financeiro.bancoDados.BancoDados;
import br.com.kushi.financeiro.model.Lancamento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Hiro
 */
@Stateless
public class FinanceiroBean implements FinanceiroBeanLocal {
    
    @Override
    public String teste() throws Exception {
        
        return "Bean teste";
    }

    @Override
    public List<Lancamento> obtemLancamentos() throws Exception {
        
        //return dao.obtemLancmento()
        return null;
    }
}
