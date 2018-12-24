package PSO;


public class Principal {

	public static void main(String[] args){

		/* INCIANDO O AMBIENTE E SUAS VARIÁVEIS */
		Ambiente ambiente = new Ambiente();
		ambiente.setC1(0.51); // Constante do cognitivo (1.6)
		ambiente.setC2(1); // Constante do social (1.98)
		ambiente.setW(0.5); // Constante de inércia (1.2)
		ambiente.setMaxiter(15); // Numero Maximo de Iteracoes
		ambiente.setQtdeparticulas(3000); // Quantidade de particulas
		ambiente.setLimiteSup(10); // Limite Superior
		ambiente.setLimiteInf(-10); // Limite Inferior
		ambiente.setF("rastrigin"); // Define funcao (esfera, rosenbrock, rastrigin)

		/* INCIANDO AS PARTICULAS E SUAS POSICOES INICIAIS */
		Enxame enxameP = new Enxame(ambiente);

		/* ENCONTRANDO O GBEST */
		enxameP.atGbest(enxameP.vetParticula[0]);

		for (int i = 0; i < enxameP.vetParticula.length; i++) {
			if (enxameP.vetParticula[i].getPbest().getFitnessp() < enxameP.gbest.getFitnessp()) {
				enxameP.atGbest(enxameP.vetParticula[i].getPbest()); /* CLONAR */
			}
		}

		/* MOVIMENTANDO PARTICULAS */
		for (int max = 0; max < ambiente.getMaxiter(); max++) {
			for (int i = 0; i < enxameP.vetParticula.length; i++) {

				enxameP.movimentacaoP(ambiente, enxameP, i);

				/* VERIFICANDO SE A NOVA FITNESS É MENOR QUE A FITNESS DO PBEST */
				if (enxameP.vetParticula[i].getFitnessp() < enxameP.vetParticula[i].getPbest().getFitnessp()) {
					enxameP.atPbest(enxameP.vetParticula[i], i);
				}

				// PREENCHENDO O VETOR ftmedia, COM A FITNESS DE CADA PARTICULA
				enxameP.ftmedia[max] += enxameP.vetParticula[i].getFitnessp();

			}
			enxameP.ftmedia[max] = (enxameP.ftmedia[max] / ambiente.getQtdeparticulas());

			/* VERIFICANDO SE ALGUMA FITNESS DE PBEST É MENOR QUE A FITNESS DO GBEST */
			for (int i = 0; i < enxameP.vetParticula.length; i++) {
				if (enxameP.vetParticula[i].getPbest().getFitnessp() < enxameP.gbest.getFitnessp()) {
					enxameP.atGbest(enxameP.vetParticula[i]);
				}

				/* PREENCHENDO O VETOR ftgbest, COM A FITNESS DO GBEST DE CADA INTERACAO */
				enxameP.ftgbest[max] = enxameP.gbest.getFitnessp();
			}
			System.out.println("\n Iteracao " + (max + 1) + "\n Média fitness: " + enxameP.ftmedia[max]);

			System.out.print(" Gbest fitness: " + enxameP.ftgbest[max] + " Aprox: ");
			System.out.printf("%.18f%n", enxameP.ftgbest[max]);
			System.out.println(" Coordenadas: [" + enxameP.gbest.getCoordp().getX1() + ", "
					+ enxameP.gbest.getCoordp().getX2() + "]");

		}

		System.out.print("\n\n\n Melhor FITNESS encontrada = " + enxameP.gbest.getFitnessp()+"\n Aprox: ");
		System.out.printf("%.18f%n", enxameP.gbest.getFitnessp());
		System.out.print(" Coordenadas: [" + enxameP.gbest.getCoordp().getX1() + ", "
				+ enxameP.gbest.getCoordp().getX2() + "]");
		
	}

}
