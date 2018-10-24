package Financeiro;

public class CalcularPrestacoes {
	private Price price;
	private float temporariaUm;
	private float temporariaDois;
	private float temporariaTres;
	private float a1;
	private float amortizacao;
	private float juros;	

	
	public CalcularPrestacoes(Price price, float temporariaUm, float temporariaDois, float temporariaTres, float a1) {
		this.price = price;
		this.temporariaUm = temporariaUm;
		this.temporariaDois = temporariaDois;
		this.temporariaTres = temporariaTres;
		this.a1 = a1;
	}

	public float compute() {
		this.price.parcelas = new Parcela[this.price.fin.getN()];
		
		for (int i=1; i<= this.price.fin.getN(); i++) {
			this.amortizacao = (float) (a1 * Math.pow((1+this.price.interest), i-1));
			this.juros = this.temporariaTres - this.amortizacao;
			Parcela p = new Parcela(this.temporariaTres, this.juros, this.amortizacao);
			this.price.parcelas[i-1] = p;
		}
		
		return this.temporariaTres;
	}

}
