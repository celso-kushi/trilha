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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author celso
 */
@Stateless
public class FinanceiroDAO {

    @Resource
    EJBContext context;

    @PersistenceContext(unitName = "trilha")
    private EntityManager entity;

    public FinanceiroDAO() {
    }

    public List<Lancamento> obterLancamentos() throws Exception {

        try {
            return entity.createQuery("SELECT l FROM Lancamento l", Lancamento.class).getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Integer inserir(Lancamento lancamento) throws Exception {

        try {

            lancamento = entity.merge(lancamento);            
            entity.flush();
            
            return lancamento.getId();

        } catch (Exception e) {
            context.setRollbackOnly();
            throw new Exception(e.getMessage());
        } 
    }

    public Lancamento alterar(Lancamento lancamento) throws Exception {

        try {
            
            lancamento = entity.merge(lancamento);            
            entity.flush();
            
            return lancamento;

        } catch (Exception e) {
            context.setRollbackOnly();
            throw new Exception(e.getMessage());
        } 
    }

    public Boolean excluir(Lancamento lancamento) throws Exception {

        try {
            entity.remove(lancamento);            
            entity.flush();
            
            return true;

        } catch (Exception e) {
            context.setRollbackOnly();
            throw new Exception(e.getMessage());
        } 
    }

    public Lancamento obtemUnico(int id) throws Exception {

        try {

            return entity.find(Lancamento.class, id);

        } catch (Exception e) {
            context.setRollbackOnly();
            throw new Exception(e.getMessage());
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
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private StringBuilder montarQuery(java.util.Date dataInicial, java.util.Date dataFinal, String nome, Integer tipo) throws Exception {

        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT * FROM financeiro WHERE 1 = 1");

            if (dataInicial != null && dataFinal != null) {
                sb.append(" AND data >= '").append(new Date(dataInicial.getTime())).append("' AND data <= '").append(new Date(dataFinal.getTime())).append("'");
            }

            if (nome != null) {
                sb.append(" AND UPPER(nome) LIKE '%").append(nome.toUpperCase()).append("%'");
            }

            if (tipo != null) {
                sb.append(" AND tipo = ").append(tipo);
            }

            return sb;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
