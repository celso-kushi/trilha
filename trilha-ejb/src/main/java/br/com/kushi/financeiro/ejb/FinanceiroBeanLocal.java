/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.ejb;

import br.com.kushi.financeiro.model.Lancamento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hiro
 */
@Local
public interface FinanceiroBeanLocal {

    String teste() throws Exception ;
    
    List<Lancamento> obtemLancamentos() throws Exception;
}
