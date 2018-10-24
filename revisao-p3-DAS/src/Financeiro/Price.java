package Financeiro;

public class Price extends Modalidade {
	public Price(String banco, float i) {
		super(banco, i);
	}

	public float calcularPrestacoes() {
		parcelas = new Parcela[fin.getN()];
		
		final float temporariaUm = (float) (Math.pow((1+interest), fin.getN()) * interest);
		final float temporariaDois = (float) (temporariaUm/(Math.pow((1+interest), fin.getN()) - 1));
		final float temporariaTres = fin.getPv() * temporariaDois;
		
		final float a1 = temporariaTres - fin.getPv() * interest;
		
		CalcularPrestacoes calcularPrestacoes = new CalcularPrestacoes(this,
				temporariaUm,
				temporariaDois,
				temporariaTres,
				a1);
		
		return calcularPrestacoes.compute();
	}
	
}
