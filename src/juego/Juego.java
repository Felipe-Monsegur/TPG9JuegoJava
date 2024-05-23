package juego;

import java.util.ArrayList;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Fondo fondo;
	private Ladrillo[][] pisos;
	private Princesa princesa;
	private Balas balas;
	private Trex trex;
	private Hueso hueso;
//	private Trex[][] trexs; //aparecer muchos rexs
//	 private ArrayList<Hueso> huesos; //muchos huesos
	private Random random;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Princesa Saltarina - Grupo 9 - Monsegur - Moragues - Escalante - V0.50", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		this.fondo= new Fondo(entorno.ancho()/2, entorno.alto()/2);
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
	                                    pisos[i][j + k] = new Ladrillo((j + k) * 50 + 25, entorno.alto() + 120 - ((i + 1) * 140), 50, 50, entorno, tipoBloque);
	                                }
	                            }
	                            metales += 3;
	                            j += 2; // Avanzar el índice para no sobrescribir los ladrillos de metal
	                            continue;
	                        } else {
	                            tipoBloque = 1;//Ladrillo
	                        }
	                    } else {
	                        tipoBloque = 1; 
	                    }
	                }
	                pisos[i][j] = new Ladrillo(j * 50 + 25, entorno.alto() + 120 - ((i + 1) * 140), 50, 50, entorno, tipoBloque);
	            }
	        }
		
	
        this.princesa = new Princesa(entorno.ancho()/2, entorno.alto() - 70); 
        this.balas = null;
        this.trex = new Trex(entorno.ancho() - 20, entorno.alto() - 70);
        this.hueso = null;
        
//        Random random = new Random();
//        this.trexs = new Trex[4][2]; // Crear 2 rexs por piso
//        for (int i = 0; i < trexs.length; i++) {
//            for (int j = 0; j < trexs[i].length; j++) {    
//                trexs[i][j] = new Trex(random.nextInt(entorno.ancho()), entorno.alto() - (70 + i * 140));// Genera un número aleatorio entre 0 y el ancho del entorno
//            }
//        }
     
        
	     
		// Inicia el juego!
		this.entorno.iniciar();		
	}
	

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		fondo.dibujar(entorno);
		
		//dibuja cada Ladrillo
		for (Ladrillo[] piso : pisos) {
	        for (Ladrillo ladrillo : piso) {
	            
	                ladrillo.dibujar(entorno);
	            
	        }
	    }
		 
//		 for (int i = 0; i < trexs.length; i++) {
//	            for (int j = 0; j < trexs[i].length; j++) {
//	                if (trexs[i][j] != null) {
//	                    trexs[i][j].dibujar(entorno);
//	                    trexs[i][j].mover(pisos, entorno);
////	                        Hueso nuevoHueso = trexs[i][j].dispararHueso();
////	                        if (nuevoHueso != null) {
////	                            huesos.add(nuevoHueso);
//	                        }
//	                    }
//	                }
	            
		
//	        
//		 // Lanzar huesos desde los Trex
//		    for (int i = 0; i < trexs.length; i++) {
//		        for (int j = 0; j < trexs[i].length; j++) {
//		            if (trexs[i][j] != null) {
//		                trexs[i][j].dibujar(entorno);
//		                trexs[i][j].mover(pisos, entorno);
//		            }
//		        }
//		    }
//
//		    // Eliminar huesos que chocan con el entorno
//		    for (int i = 0; i < huesos.size(); i++) {
//		        Hueso hueso = huesos.get(i);
//		        hueso.dibujar(entorno);
//		        hueso.mover();
//		        if (hueso.chocaConEntorno(entorno)) {
//		            huesos.remove(i);
//		            i--; // Decrementar el índice para compensar el cambio en la lista
//		        }
//		    }
		    
		 
		 if (this.trex != null) {
				this.trex.dibujar(entorno);
				this.trex.mover(pisos, entorno);
			}

			Random rand = new Random();
			if (rand.nextInt(45) == 3 && this.hueso == null) {
				this.hueso = trex.dispararHueso();
			}

			if (hueso != null) {
				hueso.dibujar(entorno);
				hueso.mover();
				if (this.hueso.getX() > entorno.ancho() || this.hueso.getX() < 0) {
					this.hueso = null;
				}
			}
		  
		 
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

		    // Dibujar la princesa después de los pisos para que aparezca encima de ellos
		    princesa.dibujar(entorno);
		    
		 // Dibujar y mover balas si están activas
		    if (entorno.sePresiono(entorno.TECLA_ESPACIO) && this.balas == null) {
		        this.balas = princesa.disparar();
		    }
		    
		    if (this.balas != null) {
	            this.balas.dibujar(entorno);
	            this.balas.mover();
	            if (this.balas.getX() > entorno.ancho() || this.balas.getX() < 0) {
	                this.balas = null;
	            }
	        }
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
