package br.com.kushi.financeiro.dao;

import br.com.kushi.financeiro.bancoDados.BancoDados;
import br.com.kushi.financeiro.model.Lancamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;

/**
 *
 * @author celso
 */
@Stateless
public class FinanceiroDAO {
    
    @Resource
    EJBContext context;
    
    public List<Lancamento> obterLancamentos() throws Exception {
        
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
                lancamento = new Lancamento(rs.getInt("id"), rs.getString("nome"), rs.getDate("data"), rs.getDouble("valor"), rs.getInt("tipo"));
                lancamentos.add(lancamento);
            }
            
            return lancamentos;
        
        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (conn != null)   conn.close();
        }
    }

    
    public Boolean inserir(Lancamento lancamento) throws Exception {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = BancoDados.conectar();

            StringBuilder sb = new StringBuilder();
            
            sb.append("INSERT INTO financeiro (nome, data, valor, tipo) values (?,?,?,?)");

            ps = conn.prepareStatement(sb.toString());
            ps.setString(1, lancamento.getNome());
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setDouble(3, lancamento.getValor());
            ps.setInt(4, lancamento.getTipo());
            
            return (ps.executeUpdate() > 0);
            
        } catch (Exception e) {
            context.setRollbackOnly();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null)   conn.close();
        }
    }

    public Lancamento alterar(Lancamento lancamento) throws Exception {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = BancoDados.conectar();

            StringBuilder sb = new StringBuilder();
            
            sb.append("UPDATE financeiro SET nome = ?, data = ?, valor = ?, tipo = ? WHERE id = ?");

            ps = conn.prepareStatement(sb.toString());
            ps.setString(1, lancamento.getNome());
            ps.setDate(2, new Date(lancamento.getData().getTime()));
            ps.setDouble(3, lancamento.getValor());
            ps.setInt(4, lancamento.getTipo());
            ps.setInt(5, lancamento.getId());
            
            ps.executeUpdate();
            
            return obtemUnico(lancamento.getId());
        
        } catch (Exception e) {
            context.setRollbackOnly();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null)   conn.close();
        }
    }
    
    public Boolean excluir(Lancamento lancamento) throws Exception {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = BancoDados.conectar();

            StringBuilder sb = new StringBuilder();
            
            sb.append("DELETE FROM financeiro WHERE id = ?");

            ps = conn.prepareStatement(sb.toString());
            ps.setInt(1, lancamento.getId());
            
            return (ps.executeUpdate() > 0);
        
        } catch (Exception e) {
            context.setRollbackOnly();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null)   conn.close();
        }
    }
    
    public Lancamento obtemUnico(int id) throws Exception {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Lancamento lancamento = null;
        
        try {

            conn = BancoDados.conectar();

            StringBuilder sb = new StringBuilder();
            
            sb.append("SELECT * FROM financeiro WHERE id = ?");

            ps = conn.prepareStatement(sb.toString());
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                lancamento = new Lancamento(rs.getInt("id"), rs.getString("nome"), rs.getDate("data"), rs.getDouble("valor"), rs.getInt("tipo"));
            }
            
            return lancamento;
            
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null)   conn.close();
        }
    }
    
    public List<Lancamento> obterLancamentos(java.util.Date dataInicial, java.util.Date dataFinal, String nome, Integer tipo) throws Exception {
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        List<Lancamento> lancamentos = new ArrayList<Lancamento>();
        Lancamento lancamento = null;
        try {

            conn = BancoDados.conectar();

            StringBuilder sb = montarQuery(dataInicial, dataFinal, nome, tipo);
            st = conn.createStatement();
            rs = st.executeQuery(sb.toString());              
            
            while (rs.next()) {
                lancamento = new Lancamento(rs.getInt("id"), rs.getString("nome"), rs.getDate("data"), rs.getDouble("valor"), rs.getInt("tipo"));
                lancamentos.add(lancamento);
            }
            
            return lancamentos;
        
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null)   conn.close();
        }
    }
    
    private StringBuilder montarQuery (java.util.Date dataInicial, java.util.Date dataFinal, String nome, Integer tipo) throws Exception {
        
        try {
            StringBuilder sb = new StringBuilder();
            
            sb.append("SELECT * FROM financeiro WHERE 1 = 1");
            
            if (dataInicial != null && dataFinal != null) 
                sb.append(" AND data >= '").append(new Date(dataInicial.getTime())).append("' AND data <= '").append(new Date(dataFinal.getTime())).append("'");
            
            
            if (nome != null) 
                sb.append(" AND UPPER(nome) LIKE '%").append(nome.toUpperCase()).append("%'");
            
            if (tipo != null) 
                sb.append(" AND tipo = ").append(tipo);
            
            return sb;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
