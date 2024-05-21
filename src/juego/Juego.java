package juego;

import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Ladrillo[][] pisos;
	private Princesa princesa;
	private Fondo fondo;
	
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

	            for (int j = 0; j < pisos[i].length; j++) {
	                int randomX = rand.nextInt(3) + 1; // Random si es Metal o Ladrillo, si es 3 Es metal
	                int tipoLadrillo = randomX == 3 ? 3 : 1; // Si es 3, el ladrillo es metal, de lo contrario es ladrillo normal
	                pisos[i][j] = new Ladrillo((j * 50) + 25, entorno.alto() + 120 -((i + 1) * 140), 50, 50, entorno, tipoLadrillo);
	            }
	        }
		
	
        this.princesa = new Princesa(entorno.ancho()/2, entorno.alto()/2); 
	     
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
		  
//		ladrillo.dibujar(entorno);
//		for (Ladrillo ladrillo : fila) {
//			ladrillo.dibujar(entorno); // Dibujar cada piso en cada iteración del juego
//        }
		
//		princesa.dibujar(entorno);
//		// Verificar colisión y actualizar princesa
////	    if (princesa.colisionConElementos(pisos)) {
////	        princesa.setEnelaire(false); // Detener el salto si hay colisión
////	    }
//	    
//		
//	    if(entorno.estaPresionada('d'))
//	        princesa.moverDerecha(entorno);
//	    
//	    if(entorno.estaPresionada('a'))
//	        princesa.moverIzquierda();
//	    
//	    if (entorno.estaPresionada('w')) {
//            princesa.saltar();
//        }
//        princesa.actualizar(entorno, ladrillos, suelo);
//        princesa.dibujar(entorno);
//	    
//	    
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
