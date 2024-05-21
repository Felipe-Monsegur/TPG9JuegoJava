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
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Titulo de TP - Grupo 9 - Monsegur - Apellido2 -Apellido3 - V0.01", 800, 600);
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
	                        int randomX = rand.nextInt(4); 
	                        if (randomX == 3) { // Solo cuando randomX es 3 se crean 3 ladrillos de metal seguidos
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
		
	
        this.princesa = new Princesa(entorno.ancho()/2, entorno.alto() - 100); 
        this.balas = null;
        this.trex = new Trex(entorno.ancho(), entorno.alto() - 100); 
	     
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
//		suelo.dibujar(entorno);
		
		 for (Ladrillo[] piso : pisos) {
	            for (Ladrillo ladrillo : piso) {
	                ladrillo.dibujar(entorno);
	            }
	        }
		  
		 trex.dibujar(entorno);
		 trex.mover(pisos, entorno);
		 
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

		    // Dibujar y mover balas si están activas
		    if (entorno.sePresiono(entorno.TECLA_ESPACIO) && this.balas == null) {
		        this.balas = princesa.disparar();
		    }
		    
		 
		 // Actualizar posición y estado de la princesa
		    princesa.mover(pisos); // Colisionar con todos los pisos

		    // Dibujar la princesa después de los pisos para que aparezca encima de ellos
		    princesa.dibujar(entorno);
		    
		    
		    if (this.balas != null) {
		        this.balas.dibujar(entorno);
		        this.balas.mover();
		        if (this.balas.getX() > entorno.ancho()) {
		            this.balas = null;
		        }
		    }    
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
