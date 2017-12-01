package br.com.kushi.financeiro.dao;

import br.com.kushi.financeiro.bancoDados.BancoDados;
import br.com.kushi.financeiro.model.Lancamento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celso
 */
public class FinanceiroDAO {
    
    public List<Lancamento> obtemLancamentos() throws Exception {
        
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        List<Lancamento> lancamentos = new ArrayList<Lancamento>();
        Lancamento lancamento = null;
        try {

            conn = BancoDados.conectar();

            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT * FROM financeiro");
            
            while (rs.next()) {
                lancamento = new Lancamento(rs.getString("nome"), rs.getDate("data"), rs.getDouble("valor"), rs.getInt("tipo"));
            }
            return lancamentos;
        
        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (conn != null)   conn.close();
        }
    }
}
