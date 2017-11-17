/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.ejb;

import javax.ejb.Stateless;

/**
 *
 * @author Hiro
 */
@Stateless
public class FinanceiroBean implements FinanceiroBeanLocal {

    @Override
    public String teste() {
        return "Bean teste";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
