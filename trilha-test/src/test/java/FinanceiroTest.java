import br.com.kushi.financeiro.bancoDados.BancoDados;
import br.com.kushi.financeiro.dao.FinanceiroDAO;
import br.com.kushi.financeiro.ejb.FinanceiroBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.kushi.financeiro.ejb.Financeiro;
import br.com.kushi.financeiro.model.Lancamento;
import br.com.kushi.financeiro.model.TipoLancamentoEnum;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.ejb.EJB;
import org.junit.Before;


/**
 *
 * @author hiro
 */
@RunWith(Arquillian.class)
public class FinanceiroTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addPackage(FinanceiroBean.class.getPackage())
            .addPackage(FinanceiroDAO.class.getPackage())
            .addPackage(BancoDados.class.getPackage())
            .addPackage(Lancamento.class.getPackage())    
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Before
    public void limparBase() throws Exception {
        Connection con = BancoDados.conectar();
        con.createStatement().executeQuery("DELETE FROM financeiro");
    }
    
    @EJB
    Financeiro financeiro;
    
    @Test
    public void obterLancamentos() throws Exception {
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao("Teste 1");
        lancamento.setValor(5d);
        lancamento.setTipo(TipoLancamentoEnum.CREDITO.getId());
        
        financeiro.inserir(lancamento);
        
        lancamento = new Lancamento();
        lancamento.setDescricao("Teste 2");
        lancamento.setValor(10d);
        lancamento.setTipo(TipoLancamentoEnum.DEBITO.getId());
        
        financeiro.inserir(lancamento);
        
        List<Lancamento> lancamentos = financeiro.obterLancamentos();
        Assert.assertEquals(2, lancamentos.size());
    }
    
    @Test
    public void inserirLancamentoNulo() throws Exception {
        Lancamento lancamento = null;
        Assert.assertNull(financeiro.inserir(lancamento));
    }
    
    @Test
    public void inserirLancamentoNomeNulo() throws Exception {
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao(null);
        try {
            financeiro.inserir(lancamento);
        } catch (Exception e) {
            Assert.assertEquals("Nome não informado!", e.getMessage());
        }
    }
    
    @Test
    public void inserirLancamentoValorNulo() throws Exception {
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao("Teste");
        lancamento.setValor(null);
        try {
            financeiro.inserir(lancamento);
        } catch (Exception e) {
            Assert.assertEquals("Valor não informado!", e.getMessage());
        }
    }
    
    @Test
    public void inserirLancamentoValorNegativo() throws Exception {
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao("Teste");
        lancamento.setValor(-5d);
        try {
            financeiro.inserir(lancamento);
        } catch (Exception e) {
            Assert.assertEquals("Valor menor que zero!", e.getMessage());
        }
    }
    
    @Test
    public void inserirLancamentoTipoZerado() throws Exception {
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao("Teste");
        lancamento.setValor(5d);
        
        try {
            financeiro.inserir(lancamento);
        } catch (Exception e) {
            Assert.assertEquals("Tipo inválido!", e.getMessage());
        }
    }
    
    @Test
    public void inserirLancamento() throws Exception {
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao("Teste Inserir");
        lancamento.setValor(5d);
        lancamento.setTipo(TipoLancamentoEnum.CREDITO.getId());
        
        int id = financeiro.inserir(lancamento);
        
        Lancamento lancamentoAssert = financeiro.obtemUnico(id);
        
        Assert.assertEquals(id, lancamentoAssert.getId());
        Assert.assertEquals(lancamento.getDescricao(), lancamentoAssert.getDescricao());
        Assert.assertEquals(lancamento.getValor(), lancamentoAssert.getValor());
        Assert.assertEquals(lancamento.getTipo(), lancamentoAssert.getTipo());
    }
    
    
    @Test
    public void alterarLancamento() throws Exception {
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao("Teste Alterar");
        lancamento.setValor(5d);
        lancamento.setTipo(TipoLancamentoEnum.CREDITO.getId());
        
        int id = financeiro.inserir(lancamento);
        
        lancamento = financeiro.obtemUnico(id);
        lancamento.setDescricao("Teste Alterado");
        lancamento.setValor(15d);
        lancamento.setTipo(TipoLancamentoEnum.DEBITO.getId());
        
        Lancamento lancamentoAlterado = financeiro.alterar(lancamento);
        
        Assert.assertEquals(id, lancamentoAlterado.getId());
        Assert.assertEquals(lancamento.getDescricao(), lancamentoAlterado.getDescricao());
        Assert.assertEquals(lancamento.getValor(), lancamentoAlterado.getValor());
        Assert.assertEquals(lancamento.getTipo(), lancamentoAlterado.getTipo());
    }
    

}
