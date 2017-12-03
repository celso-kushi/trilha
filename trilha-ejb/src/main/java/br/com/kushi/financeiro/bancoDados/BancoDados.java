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
import org.hsqldb.server.Server;

/**
 *
 * @author Hiro
 */
public class BancoDados {

    private static Server server = null;
    
    public static Connection conectar()  {
        
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/trilhadb", "SA", "");
        } catch (Exception ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, "Falha ao conectar no banco de dados", ex.getMessage());
        }
        return null;
    }
}
