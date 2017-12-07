
import br.com.kushi.financeiro.bancoDados.BancoDados;
import br.com.kushi.financeiro.dao.FinanceiroDAO;
import br.com.kushi.financeiro.ejb.FinanceiroBean;
import br.com.kushi.financeiro.ejb.FinanceiroBeanLocal;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author hiro
 */
@RunWith(Arquillian.class)
public class FinanceiroTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addClasses(FinanceiroBean.class, BancoDados.class, FinanceiroBeanLocal.class, FinanceiroDAO.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    FinanceiroBeanLocal financeiro;
    
    @Test
    public void teste() throws Exception {
        
        Assert.assertEquals("Testes aprovados", financeiro.teste());
        
    }

}
