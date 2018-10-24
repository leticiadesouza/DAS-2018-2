package tst;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Financeiro.Financiamento;
import Financeiro.Modalidade;
import financeiroExceptions.PrestacaoMaiorQueMargemException;

@RunWith(Parameterized.class)
public class TstCalculoPrice {

	Modalidade mod;
	Financiamento fin;
	
	float pv;
	int n;
	float i; 
	float pmtEsperado;

	private String banco;
	private float jurosEsperados;
	
	@Parameters
	public static Collection<Object[]> parametros() {
		Object[][] parametros = {
				{"Banco X", "10000", "10", "2.0", "1113.26", "1132.65"},
				{"Banco X", "10000", "12", "1.8", "934.01",  "1208.23"},
				{"Banco X", "10000", "15", "1.7", "760.89",  "1413.43"},
				{"Banco X", "10000", "20", "1.5", "582.45",  "1649.14"},
		};
		return Arrays.asList(parametros);
	}
	
	public TstCalculoPrice(String banco, String pv, String n, String i, String pmtEsperado, String totalJuros) {
		this.banco = banco;
		this.pv = Float.parseFloat(pv);
		this.n = Integer.parseInt(n);
		this.i = Float.parseFloat(i) / 100;
		this.pmtEsperado = Float.parseFloat(pmtEsperado);
		this.jurosEsperados = Float.parseFloat(totalJuros) ;
	}
	
	@Before
	public void setup() throws PrestacaoMaiorQueMargemException {
		fin = Financiamento.criarFinanciamento(pv, n, (pv/n));
		mod = Modalidade.criarSimulacao("Price", banco, i);
		fin.adicionarSimulacao(mod);
		mod.adicionarFinanciamento(fin);
	}
	
	@Test
	public void testCalculoParcelas() {
		float pmt = mod.calcularPrestacoes();
		float juros = mod.calcularTotalJuros();
		assertEquals(pmtEsperado, pmt, 0.01);
		assertEquals(jurosEsperados, juros, 0.01);
	}

}
