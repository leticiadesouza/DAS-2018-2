package Financeiro;

public abstract class Modalidade {

	String banco;
	float interest;
    Financiamento fin;
    Parcela[] parcelas;

	Modalidade(String banco, float i) {
		this.banco = banco;
		this.interest = i;
	}
	
	public static Modalidade criarSimulacao(String modalidade, String banco, float i) {
		Modalidade resposta = null;
		if (modalidade.equalsIgnoreCase("Price"))
			resposta = new Price(banco, i);
		else 
			resposta = null;
		return resposta;
	}

	public abstract float calcularPrestacoes() ;

	public void adicionarFinanciamento(Financiamento fin) {
		this.fin = fin;
	}
	
	public Parcela[] getParcelas() {
		return parcelas;
	}

	public float calcularTotalJuros() {
		return fin.calcularTotalJuros(parcelas);
	}
}
