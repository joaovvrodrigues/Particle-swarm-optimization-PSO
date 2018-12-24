/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psointerativo;

import java.util.Random;

public class Enxame {
	Particula[] vetParticula;
	Particula gbest;
	double[] ftmedia;
	double[] ftgbest;

	public Enxame(Ambiente amb) {

		vetParticula = new Particula[amb.getQtdeparticulas()];
		gbest = new Particula();
		Random r = new Random();
		ftmedia = new double[amb.getMaxiter()];
		ftgbest = new double[amb.getMaxiter()];

		/* INCIANDO PARTICULAS */
		for (int i = 0; i < this.vetParticula.length; i++) {
			this.vetParticula[i] = new Particula();
		}

		/* INCIANDO POSICAO DAS PARTICULAS */
		for (int i = 0; i < this.vetParticula.length; i++) {

			this.vetParticula[i].getCoordp()
					.setX1((amb.getLimiteInf() + (amb.getLimiteSup() - amb.getLimiteInf()) * r.nextDouble()));
			this.vetParticula[i].getCoordp()
					.setX2((amb.getLimiteInf() + (amb.getLimiteSup() - amb.getLimiteInf()) * r.nextDouble()));

			this.vetParticula[i].setFitnessp(amb.funcao(this.vetParticula[i].getCoordp()));

			this.vetParticula[i].getVelocidadep()
					.setX1((amb.getLimiteInf() + (amb.getLimiteSup() - amb.getLimiteInf()) * r.nextDouble()));
			this.vetParticula[i].getVelocidadep()
					.setX2((amb.getLimiteInf() + (amb.getLimiteSup() - amb.getLimiteInf()) * r.nextDouble()));

			this.vetParticula[i].setPbest(new Particula());
			this.atPbest(this.vetParticula[i], i);

		}

	}

	public void atGbest(Particula pbest) {
		this.gbest.getCoordp().setX1(pbest.getCoordp().getX1());
		this.gbest.getCoordp().setX2(pbest.getCoordp().getX2());
		this.gbest.setFitnessp(pbest.getFitnessp());
		this.gbest.getVelocidadep().setX1(pbest.getVelocidadep().getX1());
		this.gbest.getVelocidadep().setX2(pbest.getVelocidadep().getX2());
	}

	public void atPbest(Particula particula, int i) {
		this.vetParticula[i].getPbest().getCoordp().setX1(particula.getCoordp().getX1());
		this.vetParticula[i].getPbest().getCoordp().setX2(particula.getCoordp().getX2());
		this.vetParticula[i].getPbest().setFitnessp(particula.getFitnessp());
		this.vetParticula[i].getPbest().getVelocidadep().setX1(particula.getVelocidadep().getX1());
		this.vetParticula[i].getPbest().getVelocidadep().setX2(particula.getVelocidadep().getX2());
	}

	public void movimentacaoP(Ambiente ambiente, Enxame enxameP, int i) {

		Coordenada vetorinercia = new Coordenada();
		Coordenada vetorlocal = new Coordenada();
		Coordenada vetorglobal = new Coordenada();

		/* CALCULO VETOR INERCIA */
		vetorinercia.setX1((ambiente.getW() * enxameP.vetParticula[i].getVelocidadep().getX1()));
		vetorinercia.setX2((ambiente.getW() * enxameP.vetParticula[i].getVelocidadep().getX2()));

		/* CALCULO VETOR LOCAL */
		vetorlocal.setX1(ambiente.getC1() * (enxameP.vetParticula[i].getPbest().getCoordp().getX1()
				- enxameP.vetParticula[i].getCoordp().getX1()));
		vetorlocal.setX2(ambiente.getC1() * (enxameP.vetParticula[i].getPbest().getCoordp().getX2()
				- enxameP.vetParticula[i].getCoordp().getX2()));

		/* CALCULO VETOR GLOBAL */
		vetorglobal.setX1(
				(ambiente.getC2() * (enxameP.gbest.getCoordp().getX1() - enxameP.vetParticula[i].getCoordp().getX1())));
		vetorglobal.setX2(
				(ambiente.getC2() * (enxameP.gbest.getCoordp().getX2() - enxameP.vetParticula[i].getCoordp().getX2())));

		/* CALCULO DA VELOCIDADE */
		enxameP.vetParticula[i].getVelocidadep()
				.setX1((vetorinercia.getX1() + vetorlocal.getX1() + vetorglobal.getX1()));
		enxameP.vetParticula[i].getVelocidadep()
				.setX2((vetorinercia.getX2() + vetorlocal.getX2() + vetorglobal.getX2()));

		/* ATUALIZANDO A COORDENADA DA PARTICULA */
		enxameP.vetParticula[i].getCoordp().setX1(
				(enxameP.vetParticula[i].getCoordp().getX1() + enxameP.vetParticula[i].getVelocidadep().getX1()));
		enxameP.vetParticula[i].getCoordp().setX2(
				(enxameP.vetParticula[i].getCoordp().getX2() + enxameP.vetParticula[i].getVelocidadep().getX2()));

		/* CALCULANDO A FITNESS DA NOVA COORDENADA */
		enxameP.vetParticula[i].setFitnessp(ambiente.funcao(enxameP.vetParticula[i].getCoordp()));

	}
}
