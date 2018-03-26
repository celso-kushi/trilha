package br.com.kushi.financeiro.dao;

import br.com.kushi.financeiro.model.Lancamento;
import java.sql.Date;
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
            throw new Exception(e.getMessage());
        }
    }

    public List<Lancamento> obterLancamentos(java.util.Date dataInicial, java.util.Date dataFinal, String nome, Integer tipo) throws Exception {

        try {
            StringBuilder sb = montarQuery(dataInicial, dataFinal, nome, tipo);
            return entity.createQuery(sb.toString(), Lancamento.class).getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private StringBuilder montarQuery(java.util.Date dataInicial, java.util.Date dataFinal, String nome, Integer tipo) throws Exception {

        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT l FROM Lancamento l WHERE 1 = 1");

            if (dataInicial != null && dataFinal != null) {
                sb.append(" AND l.data >= '").append(new Date(dataInicial.getTime())).append("' AND l.data <= '").append(new Date(dataFinal.getTime())).append("'");
            }

            if (nome != null) {
                sb.append(" AND UPPER(l.nome) LIKE '%").append(nome.toUpperCase()).append("%'");
            }

            if (tipo != null) {
                sb.append(" AND l.tipo = ").append(tipo);
            }

            return sb;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
