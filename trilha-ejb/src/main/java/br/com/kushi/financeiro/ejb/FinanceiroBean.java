/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi.financeiro.ejb;

import br.com.kushi.financeiro.dao.FinanceiroDAO;
import br.com.kushi.financeiro.model.Filtro;
import br.com.kushi.financeiro.model.Lancamento;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Hiro
 */
@Stateless
public class FinanceiroBean implements Financeiro {

    @EJB
    FinanceiroDAO financeiroDAO;

    @Override
    public List<Lancamento> obterLancamentos() throws Exception {

        return financeiroDAO.obterLancamentos();
    }

    @Override
    public Integer inserir(Lancamento lancamento) throws Exception {

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
        
        if (filtro.getDataInicial() == null && filtro.getDataFinal() == null)
            throw new Exception("Informe a data inicial e data final");
        
        return financeiroDAO.obterLancamentos(filtro.getDataInicial(), filtro.getDataFinal(), null, null);
    }

    @Override
    public List<Lancamento> obterLancamentosPorNome(Filtro filtro) throws Exception {
        
        if (filtro.getNome() == null)
            throw new Exception ("Informe o nome");
        
        return financeiroDAO.obterLancamentos(null, null, filtro.getNome(), null);
    }

    @Override
    public List<Lancamento> obterLancamentosPorTipo(Filtro filtro) throws Exception {
        
        if (filtro.getTipo()== null)
            throw new Exception ("Informe o tipo");
        
        return financeiroDAO.obterLancamentos(null, null, null, filtro.getTipo());
    }
    
    @Override
    public Lancamento obtemUnico(int idLancamento) throws Exception {
        
        return financeiroDAO.obtemUnico(idLancamento);
        
    }

}
