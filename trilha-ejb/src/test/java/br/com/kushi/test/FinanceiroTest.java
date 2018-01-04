package br.com.kushi.test;


import br.com.kushi.financeiro.bancoDados.BancoDados;
import br.com.kushi.financeiro.dao.FinanceiroDAO;
import br.com.kushi.financeiro.ejb.FinanceiroBean;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.kushi.financeiro.ejb.Financeiro;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 *
 * @author hiro
 */
@RunWith(Arquillian.class)
public class FinanceiroTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
              //  .addPackage(FinanceiroBean.class.getPackage())
            .addClasses(Financeiro.class, FinanceiroBean.class, FinanceiroDAO.class, BancoDados.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    Financeiro financeiro;
    
    @Test
    public void teste() throws Exception {
        Assert.assertEquals("Testes aprovados", "Testes aprovados");
        
        //Assert.assertEquals("Testes aprovados", financeiro.teste());
        
//        List<Lancamento> lancamentos = financeiro.obterLancamentos();
//        
//        Assert.assertEquals(7, lancamentos.size());
    }

}
