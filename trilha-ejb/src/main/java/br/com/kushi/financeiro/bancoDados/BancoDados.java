/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.hsqldb.server.Server;

/**
 *
 * @author Hiro
 */
public class BancoDados {

    private static Server server = null;
    
    private static DataSource ds = null;
    
    private static InitialContext ic;
    
    
    public static Connection conectar() throws Exception {
        
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:jboss/TrilhaDS");
            
            return ds.getConnection();
            
        } catch (Exception ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, "Falha ao conectar no banco de dados", ex.getMessage());
            throw ex;
        }
    }
}
