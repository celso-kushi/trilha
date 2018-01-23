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
import br.com.kushi.financeiro.model.Filtro;
import br.com.kushi.financeiro.model.Lancamento;
import java.util.List;
import javax.ejb.EJB;
import org.junit.After;
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
    
    @EJB
    Financeiro financeiro;
    
    @Test
    public void teste() throws Exception {
        Assert.assertTrue(true);
        
        Assert.assertEquals("Testes aprovados", financeiro.teste());
        
        List<Lancamento> lancamentos = financeiro.obterLancamentos();
        
        Assert.assertEquals(7, lancamentos.size());
    }

}
