
import br.com.kushi.financeiro.bancoDados.BancoDados;
import br.com.kushi.financeiro.ejb.FinanceiroBean;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author hiro
 */
@RunWith(Arquillian.class)
public class FinanceiroTest {

    @Deployment
    public static Archive<?> criarArquivoTeste() {
        Archive<?> arquivoTeste = ShrinkWrap.create(WebArchive.class, "aplicacaoTeste.war")
                // Adicionando o pacote inteiro da classe PessoaDao, ou seja incluí todas as outras classes deste pacote
                .addPackage(FinanceiroBean.class.getPackage())
                // Adicionando apenas a classe Pessoa, e não o pacote inteiro como na linha anterior
                .addClass(FinanceiroBean.class)
                // Adicionando o arquivo persistence.xml para conexão JPA
                //.addAsResource("META-INF/persistence.xml")
                // Adicionando o beans.xml para ativação do CDI
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return arquivoTeste;
    }
    
    @Inject
    private FinanceiroBean financeiroBean;
    
    @Test
    public void teste() throws Exception {
        
        financeiroBean.excluir(1);
        
    }

}
