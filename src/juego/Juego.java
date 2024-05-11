package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Suelo suelo;
	private Piso[] pisos;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Titulo de TP - Grupo 9 - Monsegur - Apellido2 -Apellido3 - V0.01", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		this.suelo = new Suelo(entorno.ancho()/2, entorno.alto()-20, entorno);
		this.pisos = new Piso[4];
        for (int i = 0; i < pisos.length; i++) {
            pisos[i] = new Piso(entorno.ancho() / 2, entorno.alto() - 160 - (i * 140), entorno.ancho(), 40, entorno);
        }
	     
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
		suelo.dibujar(entorno);
		for (Piso piso : pisos) {
            piso.dibujar(entorno); // Dibujar cada piso en cada iteración del juego
        }
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
