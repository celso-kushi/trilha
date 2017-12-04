/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.ejb;

import br.com.kushi.financeiro.bancoDados.BancoDados;
import br.com.kushi.financeiro.dao.FinanceiroDAO;
import br.com.kushi.financeiro.model.Filtro;
import br.com.kushi.financeiro.model.Lancamento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Hiro
 */
@Stateless
public class FinanceiroBean implements FinanceiroBeanLocal {

    @Inject
    FinanceiroDAO financeiroDAO;

    @Override
    public List<Lancamento> obterLancamentos() throws Exception {

        return financeiroDAO.obterLancamentos();
    }

    @Override
    public Boolean inserir(Lancamento lancamento) throws Exception {

        if (lancamento != null) {

            validarLancamento(lancamento);
            return financeiroDAO.inserir(lancamento);
        }

        return null;
    }

    @Override
    public Lancamento alterar(Lancamento lancamento) throws Exception {

        if (lancamento != null) {

            validarLancamento(lancamento);
            return financeiroDAO.alterar(lancamento);
        }

        return null;
    }

    @Override
    public Boolean excluir(Integer id) throws Exception {

        if (id != null) {
            
            Lancamento lancamento = financeiroDAO.obtemUnico(id);
            
            if (lancamento != null)
                return financeiroDAO.excluir(lancamento);
            else
                throw new Exception("Este registro não existe, impossível excluir...");
        }

        return null;
    }

    private void validarLancamento(Lancamento lancamento) throws Exception {
        if (lancamento.getNome() == null) {
            throw new Exception("Nome não informado!");
        }
        if (lancamento.getValor() == null) {
            throw new Exception("Valor não informado!");
        }
        if (lancamento.getValor() < 0d) {
            throw new Exception("Valor menor que zero!");
        }

        if (lancamento.getTipo() == 0) {
            throw new Exception("Tipo inválido!");
        }
    }

    @Override
    public List<Lancamento> obterLancamentosPorData(Filtro filtro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Lancamento> obterLancamentosPorNome(Filtro fitro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Lancamento> obterLancamentosPorTipo(Filtro fitro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
