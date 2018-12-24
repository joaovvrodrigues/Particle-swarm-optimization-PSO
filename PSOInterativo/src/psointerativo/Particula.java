/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psointerativo;

public class Particula {
	private Coordenada coordp = new Coordenada();
	private Coordenada velocidadep = new Coordenada();
	private double fitnessp;
	private Particula pbest;

	public Coordenada getCoordp() {
		return coordp;
	}

	public void setCoordp(Coordenada coordp) {
		this.coordp = coordp;
	}

	public Coordenada getVelocidadep() {
		return velocidadep;
	}

	public void setVelocidadep(Coordenada velocidadep) {
		this.velocidadep = velocidadep;
	}

	public double getFitnessp() {
		return fitnessp;
	}

	public void setFitnessp(double fitnessp) {
		this.fitnessp = fitnessp;
	}

	public Particula getPbest() {
		return pbest;
	}

	public void setPbest(Particula pbest) {
		this.pbest = pbest;
	}
}
