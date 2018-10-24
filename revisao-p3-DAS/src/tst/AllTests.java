package tst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TstCalculoPrice.class, TstInformacoesFinanciamento.class,
		TstInformacoesModalidadesFinanciamentos.class })
public class AllTests {

}
