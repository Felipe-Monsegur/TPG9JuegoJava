package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Fondo fondo;
	private Lava lava;
	private Ladrillo[][] pisos;
	private Princesa princesa;
	private Bala balas;
	private Trex[][] trexs; // aparecer muchos rexs metodo 1
	private int trexsEnPantalla;
	private Image imag;
	private int trexsEliminados;
	private int puntos;
	private int vidas;
	private boolean inmunidad; //para que no muera recien empezar el juego y sea inmune al daño hasta que se mueva
	private boolean estadoInicio;

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Super Elizabeth Sis, Volcano Edition - Grupo 9 - Monsegur - Moragues - Escalante - V1.0.0", 800,600);
		// Inicializar lo que haga falta para el juego
		// ...

		this.fondo = new Fondo(entorno.ancho() / 2, entorno.alto() / 2);
		this.pisos = new Ladrillo[5][]; // Crear 5 pisos en total
		this.estadoInicio = true; // Comienza en la pantalla de inicio
		reiniciar();
		
		// Inicia el juego!
		this.entorno.iniciar();
		}
	
		//reinicia el juego y vuelve a dibujar los pisos de manera diferente los trexs y la princesa, se reinicia el contador tmb
		public void reiniciar() {
		inmunidad=true;	//princesa inmune al daño hasta que se mueva
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
		this.lava = new Lava(entorno.ancho()/2, entorno.alto()+350, entorno);
		this.puntos = 0;	// cada trexs eliminado te da 2 puntos
		this.trexsEliminados = 0;
		this.trexsEnPantalla =0;
		this.vidas = 3;
		this.princesa = new Princesa(entorno.ancho() / 2, entorno.alto() - 70);
		this.balas = null;

//        APARECER MUCHOS REXS METODO 1
		Random random = new Random();
		this.trexs = new Trex[4][2]; // Crear 2 rexs por piso
		for (int i = 0; i < trexs.length; i++) {
			for (int j = 0; j < trexs[i].length; j++) {
				trexs[i][j] = new Trex(random.nextInt(entorno.ancho()), entorno.alto() - (70 + i * 140));// Genera un numero aleatorio para aparecer el trexs	
				this.trexsEnPantalla +=1;
			}
		}

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
		
		//antes de empezar el juego
		if (estadoInicio) {
			// Mostrar pantalla de inicio con instrucciones
			entorno.cambiarFont("Calibri", 40, java.awt.Color.magenta);
			entorno.escribirTexto("Bienvenido a Super Elizabeth Sis,", entorno.ancho() / 2 - 250, entorno.alto() / 2 - 150);
			entorno.cambiarFont("Calibri", 40, java.awt.Color.ORANGE);
			entorno.cambiarFont("Calibri", 40, java.awt.Color.ORANGE);
			entorno.escribirTexto("Volcano Edition", entorno.ancho() / 2 - 100, entorno.alto() / 2 - 110);
			entorno.cambiarFont("Calibri", 30, java.awt.Color.white);
			entorno.escribirTexto("Instrucciones:", entorno.ancho() / 2 - 200, entorno.alto() / 2 - 60);
			entorno.escribirTexto("[←] Mover a la izquierda", entorno.ancho() / 2 - 200, entorno.alto() / 2 - 30);
			entorno.escribirTexto("[→] Mover a la derecha", entorno.ancho() / 2 - 200, entorno.alto() / 2);
			entorno.escribirTexto("[C] Dispara", entorno.ancho() / 2 - 200, entorno.alto() / 2 + 30);
			entorno.escribirTexto("[X] Salta", entorno.ancho() / 2 - 200, entorno.alto() / 2 + 60);
			entorno.escribirTexto("Reglas del Juego:", entorno.ancho() / 2 - 200, entorno.alto() / 2 + 100); 
			entorno.escribirTexto("Tenes 3 vidas.", entorno.ancho() / 2 - 200, entorno.alto() / 2 + 130); 
			entorno.escribirTexto("Si un hueso te toca, pierdes una vida.", entorno.ancho() / 2 - 200, entorno.alto() / 2 + 160);
			entorno.escribirTexto("Si un Trex te toca, pierdes todas las vidas.", entorno.ancho() / 2 - 200, entorno.alto() / 2 + 190);
			entorno.escribirTexto("Presiona [Enter] para comenzar", entorno.ancho() / 2 - 180, entorno.alto() / 2 + 240);
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				estadoInicio = false; // Iniciar el juego cuando se presiona Enter
				reiniciar();
			}
			return; // Salir del método tick para no ejecutar el resto del juego hasta que se presione Enter
		}
		
		
		
		//empieza el juego
		fondo.dibujar(entorno);
		
		
		
		// dibuja cada Ladrillo
		for (Ladrillo[] piso : pisos) {
			for (Ladrillo ladrillo : piso) {
				if (ladrillo != null) {
					ladrillo.dibujar(entorno);
				}
			}
		}
		
		//mostrar puntos y vidas
		entorno.cambiarFont("Calibri", 23, java.awt.Color.black);
		entorno.escribirTexto("TREXS ELIMINADOS:" + this.trexsEliminados, 0, entorno.alto() - 24);
		entorno.escribirTexto("PUNTOS:" + this.puntos, 0, entorno.alto() - 1);
		for (int i = 0; i < vidas; i++) {
			this.imag = Herramientas.cargarImagen("styles/heart.png");
			entorno.dibujarImagen(imag, entorno.ancho() - 30 - (i * 40), entorno.alto() - 23, 0, 4);
		}

		//Aparecer muchos trexs
		for (int i = 0; i < trexs.length; i++) {
			for (int j = 0; j < trexs[i].length; j++) {
				if (trexs[i][j] != null) {
					trexs[i][j].dibujar(entorno);
					trexs[i][j].mover(pisos, entorno, trexs);
					  if (this.princesa != null && !inmunidad && trexs[i][j].colisionConPrincesa(princesa)) { //trex le saca una vida a la princesa si colisionan			             
			                vidas=0;
					  }

					// Cada rex dispara un hueso si puede
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
							this.balas = null;
						}
						// Verificar colisión entre princesa y huesos
						if (this.princesa != null && !inmunidad && hueso.colisionConPrincesa(this.princesa)) {
							trexs[i][j].setHueso(null); // Eliminar hueso si colisiona con princesa
							this.vidas -= 1;
							this.princesa.cambiarImagen();
						}

					}

				}
			}
		}
		
		
		
		//aparece 1 trexs mas si solo hay 2 trexs en pantalla
		if (this.trexsEnPantalla == 2) {
		    Random random = new Random();
		    int nuevoI = random.nextInt(4); // Obtener un índice aleatorio para la fila
		    int nuevoJ = random.nextInt(1); // Obtener un índice aleatorio para la columna
		    // Verificar que la posición no este ocupada por un trex existente
		    while (trexs[nuevoI][nuevoJ] != null) {
		        nuevoI = random.nextInt(4);
		        nuevoJ = random.nextInt(trexs[nuevoI].length);
		    }
		    // Agregar un nuevo Trex en la posición aleatoria
		    trexs[nuevoI][nuevoJ] = new Trex(random.nextInt(entorno.ancho()), entorno.alto() - (70 + nuevoI * 140));
		    this.trexsEnPantalla++;
		}
		
		//princesa muere si pierde las vidas
		if (this.vidas == 0) {
			this.princesa = null;
		}

		
		//dibuja y mueve a la princesa
		if (this.princesa != null) {
			princesa.dibujar(entorno);

			// Manejar entradas del jugador
			if (entorno.estaPresionada('d') || entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				princesa.moverDerecha(entorno);
				inmunidad=false;//princesa pierde la inmunidad al daño porque se movio
			}
			if (entorno.estaPresionada('a') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				princesa.moverIzquierda();
				inmunidad=false;//princesa pierde la inmunidad al daño porque se movio
			}
			if (entorno.estaPresionada('w') || entorno.estaPresionada('x')) {
				princesa.saltar();
				inmunidad=false;//princesa pierde la inmunidad al daño porque se movio
			}

			// Actualizar posición y estado de la princesa
			princesa.mover(pisos); // Colisionar con todos los pisos

			
			
			
			// Dibujar y mover balas si están activas
			if ((entorno.sePresiono(entorno.TECLA_ESPACIO) || entorno.sePresiono('c')) && this.balas == null) {
				this.balas = princesa.disparar();
				inmunidad=false;//princesa pierde la inmunidad al daño
			}

			
			// dibuja las balas
			if (this.balas != null) {
				this.balas.dibujar(entorno);
				this.balas.mover();

				// detecta colision de la bala con los trex
				for (int i = 0; i < trexs.length; i++) {
					for (int j = 0; j < trexs[i].length; j++) {
						if (trexs[i][j] != null && trexs[i][j].colisionConBala(this.balas)) {
							trexs[i][j] = null; // convierte en null al trex y a las balas
							this.balas = null;
							this.trexsEnPantalla-=1;
							this.trexsEliminados+=1; // Incrementar contador de trexs eliminados
							this.puntos += 2;
						}
					}

				}
				//bala desaparece si choca con lso bordes
				if (this.balas != null && (this.balas.getX() > entorno.ancho() || this.balas.getX() < 0)) {
					this.balas = null;
				}
			}
		}
		
		//subir la lava
		if(this.princesa != null && !inmunidad) {
			lava.subirLava();
			lava.dibujar(entorno);
		}
		
		if (this.princesa != null && !inmunidad && (lava.colisionConPrincesa(princesa)) ) {
			this.vidas=0;
		}
		
		//mostrar puntos y vidas
				entorno.cambiarFont("Calibri", 23, java.awt.Color.black);
				entorno.escribirTexto("TREXS ELIMINADOS:" + this.trexsEliminados, 0, entorno.alto() - 24);
				entorno.escribirTexto("PUNTOS:" + this.puntos, 0, entorno.alto() - 1);
				for (int i = 0; i < vidas; i++) {
					this.imag = Herramientas.cargarImagen("styles/heart.png");
					entorno.dibujarImagen(imag, entorno.ancho() - 30 - (i * 40), entorno.alto() - 23, 0, 4);
				}
		
		if (this.princesa == null) {
			// Marcar que perdiste el juego
			this.imag = Herramientas.cargarImagen("styles/fondonegro.png");
			entorno.dibujarImagen(imag, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
			entorno.cambiarFont("Calibri", 50, java.awt.Color.red);
			entorno.escribirTexto("PERDISTE", entorno.ancho() / 2 - 110, entorno.alto() / 2 - 50);
			entorno.cambiarFont("Calibri", 40, java.awt.Color.white);
			entorno.escribirTexto("TREXS ELIMINADOS:" + this.trexsEliminados, entorno.ancho() / 2 - 250,entorno.alto() / 2 + 100);
			entorno.escribirTexto("PUNTOS:" + this.puntos, entorno.ancho() / 2 - 250, entorno.alto() / 2 + 140);
			entorno.escribirTexto("JUEGO TERMINADO", entorno.ancho() / 2 - 180, entorno.alto() / 2);
			entorno.escribirTexto("Presiona [r] para volver a jugar", entorno.ancho() / 2 - 250,entorno.alto() / 2 + 50);
			// Detectar si se presiona la tecla 'r' para reiniciar el juego
		}
		if (this.princesa != null && princesa.getY() < 0) {
			// Marcar que ganaste el juego
			princesa.setY(-10000);
			
			this.imag = Herramientas.cargarImagen("styles/fondonegro.png");
			entorno.dibujarImagen(imag, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
			entorno.cambiarFont("Calibri", 50, java.awt.Color.green);
			entorno.escribirTexto("GANASTE", entorno.ancho() / 2 - 110, entorno.alto() / 2 - 50);
			entorno.cambiarFont("Calibri", 40, java.awt.Color.white);
			entorno.escribirTexto("TREXS ELIMINADOS:" + this.trexsEliminados, entorno.ancho() / 2 - 250,entorno.alto() / 2 + 100);
			entorno.escribirTexto("PUNTOS:" + this.puntos, entorno.ancho() / 2 - 250, entorno.alto() / 2 + 140);
			entorno.escribirTexto("JUEGO TERMINADO", entorno.ancho() / 2 - 180, entorno.alto() / 2);
			entorno.escribirTexto("Presiona [r] para volver a jugar", entorno.ancho() / 2 - 250,entorno.alto() / 2 + 50);
			// Detectar si se presiona la tecla 'r' para reiniciar el juego
		}
		
		
		//boton de reiniciar para cualquier momento del juego
		if (entorno.sePresiono('r')) {
			reiniciar();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
