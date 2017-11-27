/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author Hiro
 */
public class BancoDados {

    private static String path = "jdbc:hsqldb:file:" + System.getProperty("user.dir")+ "/database/trilhadb";
    
    public static Connection conectar() throws Exception {
        
        return DriverManager.getConnection(path, "sa", "");
    }
    
    static String sql = "SELECT * FROM financeiro";
    
    public static void main(String[] args) throws Exception {
        
        Connection conn = conectar();
        
        System.out.println("br.com.kushi.financeiro.bancoDados.BancoDados.main()");
        System.out.println(conn.toString());
        
        Statement statement = null;
        ResultSet resultSet = null;

        statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);

	while (resultSet.next()) {
	    System.out.println(resultSet.getString("nome"));
	    
	}
	
	resultSet.close();
	statement.close();
	conn.close();
    }
}
