package PSO;

public class Ambiente {

	private double c1, c2, w;
	private int maxiter;
	private int qtdeparticulas;
	private int limiteSup;
	private int limiteInf;
	private String f;

	public double getC1() {
		return c1;
	}

	public void setC1(double c1) {
		this.c1 = c1;
	}

	public double getC2() {
		return c2;
	}

	public void setC2(double c2) {
		this.c2 = c2;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public int getMaxiter() {
		return maxiter;
	}

	public void setMaxiter(int maxiter) {
		this.maxiter = maxiter;
	}

	public int getQtdeparticulas() {
		return qtdeparticulas;
	}

	public void setQtdeparticulas(int qtdeparticulas) {
		this.qtdeparticulas = qtdeparticulas;
	}

	public int getLimiteSup() {
		return limiteSup;
	}

	public void setLimiteSup(int limiteSup) {
		this.limiteSup = limiteSup;
	}

	public int getLimiteInf() {
		return limiteInf;
	}

	public void setLimiteInf(int limiteInf) {
		this.limiteInf = limiteInf;
	}

	public void setF(String f) {
		this.f = f;
	}

	// FUNCOES
	public double funcao(Coordenada coordp) {

		if (this.f == "esfera") {
			// otimo: 0
			return (Math.pow(coordp.getX1(), 2) + Math.pow(coordp.getX2(), 2));
		}
		
		if (this.f == "rosenbrock") {
			// otimo: 1
			return Math.pow(1 - coordp.getX1(), 2) + 100 * Math.pow((coordp.getX2() - Math.pow(coordp.getX1(), 2)), 2);
		}
		
		if (this.f == "rastrigin") {
			// otimo: 0

			return 10 * 2 + Math.pow(coordp.getX1(), 2) - 10.0 * Math.cos(2 * Math.PI * coordp.getX1())
					+ Math.pow(coordp.getX2(), 2) - 10.0 * Math.cos(2 * Math.PI * coordp.getX2());

			

		} else {
			return 0;
		}
	}

}
