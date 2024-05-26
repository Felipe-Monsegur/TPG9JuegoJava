package juego;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Fondo fondo;
	private Ladrillo[][] pisos;
	private Princesa princesa;
	private Balas balas;
	private Hueso hueso;
//	private Trex[] trexs; // aparecer muchos rexs
	private Trex[][] trexs; // aparecer muchos rexs metodo 1
	private Random random;
	private Image imag;


	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Princesa Saltarina - Grupo 9 - Monsegur - Moragues - Escalante - V0.50", 800,600);
		// Inicializar lo que haga falta para el juego
		// ...
		this.imag = Herramientas.cargarImagen("fondonegro.png");
		this.fondo = new Fondo(entorno.ancho() / 2, entorno.alto() / 2);
		this.pisos = new Ladrillo[5][]; // Crear 5 pisos en total
		Random rand = new Random();
		// Crear los ladrillos para cada piso
		for (int i = 0; i < pisos.length; i++) {
			pisos[i] = new Ladrillo[entorno.ancho() / 50]; // Cantidad de ladrillos por piso
			int metales = 0;
			for (int j = 0; j < pisos[i].length; j++) {
				int tipoBloque;
				if (i == 0) {
					// Para el primer piso, todos los ladrillos son normales
					tipoBloque = 1;
				} else {
					if (metales < 6 && j <= pisos[i].length - 3) {
						if (rand.nextInt(4) == 3) { // Solo cuando randomX es 3 se crean 3 ladrillos de metal seguidos
							tipoBloque = 3;
							for (int k = 0; k < 3; k++) {
								if (j + k < pisos[i].length) {
									pisos[i][j + k] = new Ladrillo((j + k) * 50 + 25,
											entorno.alto() + 120 - ((i + 1) * 140), 50, 50, entorno, tipoBloque);
								}
							}
							metales += 3;
							j += 2; // Avanzar el índice para no sobrescribir los ladrillos de metal
							continue;
						} else {
							tipoBloque = 1;// Ladrillo
						}
					} else {
						tipoBloque = 1;
					}
				}
				pisos[i][j] = new Ladrillo(j * 50 + 25, entorno.alto() + 120 - ((i + 1) * 140), 50, 50, entorno,
						tipoBloque);
			}
		}

		this.princesa = new Princesa(entorno.ancho() / 2, entorno.alto() - 70);
		this.balas = null;
		this.hueso = null;



//        APARECER MUCHOS REXS METODO 1
		Random random = new Random();
		this.trexs = new Trex[4][2]; // Crear 2 rexs por piso
		for (int i = 0; i < trexs.length; i++) {
			for (int j = 0; j < trexs[i].length; j++) {
				trexs[i][j] = new Trex(random.nextInt(entorno.ancho()), entorno.alto() - (70 + i * 140));// Genera un número aleatorio entre 0 y el anch del entorno
			}
		}

//		METODO 2 MUCHOS REXS
//		this.trexs = new Trex[8]; // 2 trex por cada fila de 4
//		Random random = new Random();
//
//		for (int i = 0; i < pisos.length - 1; i++) { // recorre los pisos menos el ultimo (el quinto)
//			int posicionx1 = random.nextInt(entorno.ancho());
//			int posicionx2 = random.nextInt(entorno.ancho());
//			int posiciony = (entorno.alto() + 120 - (i + 1) * 140)-25;
//
//			// Asignar dos Trex por cada fila de bloques
//			trexs[i * 2] = new Trex(posicionx1 , posiciony);
//			trexs[i * 2 + 1] = new Trex(posicionx2, posiciony);
//		}
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		fondo.dibujar(entorno);

		// dibuja cada Ladrillo
		for (Ladrillo[] piso : pisos) {
			for (Ladrillo ladrillo : piso) {
				if (ladrillo != null) {
					ladrillo.dibujar(entorno);
				}
			}
		}

//        APARECER MUCHOS REXS METODO 1
		for (int i = 0; i < trexs.length; i++) {
			for (int j = 0; j < trexs[i].length; j++) {
				if (trexs[i][j] != null) {
					trexs[i][j].dibujar(entorno);
					trexs[i][j].mover(pisos, entorno);

					// CADA REX DISPARA UN HUESO SI PUEDE
					trexs[i][j].dispararHueso();
					Hueso hueso = trexs[i][j].getHueso();
					if (hueso != null) {
						hueso.dibujar(entorno);
						hueso.mover();
						if (hueso.chocaConEntorno(entorno)) {
							trexs[i][j].setHueso(null); // Eliminar hueso si sale de la pantalla
						}
						// Verificar colisión entre balas y huesos
						if (this.balas != null && hueso.colisionConBala(this.balas)) {
							trexs[i][j].setHueso(null); // Eliminar hueso si colisiona con la bala
						}
						// Verificar colisión entre princesa y huesos
						if (this.princesa != null && hueso.colisionConPrincesa(this.princesa)) {
							trexs[i][j].setHueso(null); // Eliminar hueso si colisiona con la bala
							this.princesa = null;
						}

					}

				}
			}
		}

//	      METODO 1
//		 // Lanzar huesos desde los Trex
//		    for (int i = 0; i < trexs.length; i++) {
//		        for (int j = 0; j < trexs[i].length; j++) {
//		            if (trexs[i][j] != null) {
//		                trexs[i][j].dibujar(entorno);
//		                trexs[i][j].mover(pisos, entorno);
//		            }
//		        }
//		    }

		// METODO 2 PARA LOS TREXS
		// recorre la lista de trexs y los dibuja
//		for (int i = 0; i < trexs.length; i++) {
//			if (trexs[i] != null) {
//				trexs[i].dibujar(entorno);
//				trexs[i].mover(pisos, entorno);
//				trexs[i].dispararHueso();
//				// Mueve y dibuja el hueso del Trex
//				Hueso hueso = trexs[i].getHueso();
//				if (hueso != null) {
//					hueso.dibujar(entorno);
//					hueso.mover();
//					if (hueso.chocaConEntorno(entorno)) {
//						trexs[i].setHueso(null); // Eliminar hueso si sale de la pantalla
//					}
//				}
//			}
//		}
		if (this.princesa != null) {
			princesa.dibujar(entorno);

			// Manejar entrada del jugador
			if (entorno.estaPresionada('d')) {
				princesa.moverDerecha(entorno);
			}

			if (entorno.estaPresionada('a')) {
				princesa.moverIzquierda();
			}

			if (entorno.estaPresionada('w')) {
				princesa.saltar();
			}

			// Actualizar posición y estado de la princesa
			princesa.mover(pisos); // Colisionar con todos los pisos
			princesa.dibujar(entorno);

			// Dibujar y mover balas si están activas
			if (entorno.sePresiono(entorno.TECLA_ESPACIO) && this.balas == null) {
				this.balas = princesa.disparar();
			}

			// dibuja las balas
			if (this.balas != null) {
				this.balas.dibujar(entorno);
				this.balas.mover();

				// detecta colision de la bala con los trex!!!!
				for (int i = 0; i < trexs.length; i++) {
					for (int j = 0; j < trexs[i].length; j++) {
						if (trexs[i][j] != null && trexs[i][j].colisionConBala(this.balas)) {
							trexs[i][j] = null; // convierte en null al trex y a las balas
							this.balas = null;
						}
					}

				}

				if (this.balas != null && (this.balas.getX() > entorno.ancho() || this.balas.getX() < 0)) {
					this.balas = null;
				}
			}
		}
		
		 if (this.princesa == null) {
			// Marcar que el juego ha terminado
			 	entorno.dibujarImagen(imag, entorno.ancho()/2, entorno.alto()/2, 0, 1);
		        entorno.cambiarFont("Arial", 40, java.awt.Color.white);
		        entorno.escribirTexto("JUEGO TERMINADO", entorno.ancho() / 2 -180, entorno.alto() / 2);
		        entorno.escribirTexto("Presiona [r] para volver a jugar", entorno.ancho() / 2 - 250, entorno.alto() / 2 + 50);
		        // Detectar si se presiona la tecla 'r' para reiniciar el juego
		        if(entorno.sePresiono('r')) {
		        		Juego game = new Juego();
		        	}
		        }
		
		    	
		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
