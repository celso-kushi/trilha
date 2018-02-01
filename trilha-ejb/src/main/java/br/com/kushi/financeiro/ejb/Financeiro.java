package br.com.kushi.financeiro.ejb;

import br.com.kushi.financeiro.model.Filtro;
import br.com.kushi.financeiro.model.Lancamento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hiro
 */
@Local
public interface Financeiro {

    List<Lancamento> obterLancamentos() throws Exception;

    Boolean inserir(Lancamento lancamento) throws Exception;

    Lancamento alterar(Lancamento lancamento) throws Exception;

    Boolean excluir(Integer id) throws Exception;
    
    List<Lancamento> obterLancamentosPorData(Filtro fitro) throws Exception;
    
    List<Lancamento> obterLancamentosPorNome(Filtro fitro) throws Exception;
    
    List<Lancamento> obterLancamentosPorTipo(Filtro fitro) throws Exception;
    
}
