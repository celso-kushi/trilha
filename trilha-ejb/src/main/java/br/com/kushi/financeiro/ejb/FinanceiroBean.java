/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.ejb;

import br.com.kushi.financeiro.bancoDados.BancoDados;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public String obtemLancamentos() throws Exception {
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        String resultado = new String();
        
        try {
            conn = BancoDados.conectar();

            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM financeiro");

            while (resultSet.next()) {
                resultado += resultSet.getString("nome");
                
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (conn != null)   conn.close();
        }
        
        return resultado;
    }
}
