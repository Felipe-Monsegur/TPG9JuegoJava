package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Trex {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Color color;
	private int direccion;
	private double velocidadY;
	private double gravedad;
	private boolean enelaire;
	private Random random;
	private Hueso hueso;

	public Trex(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 40;
		this.alto = 50;
		this.color = Color.green;
		this.direccion = 1;
		this.velocidadY = 0;
		this.gravedad = 0.5;
		this.enelaire = false;
	    this.random = new Random();

	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
	}

	public void mover(Ladrillo[][] pisos, Entorno e) {
		boolean ensuelo = false; // verifica si la princesa está en el suelo
		// verifica colisiones con bloques en todos los pisos
		for (Ladrillo[] piso : pisos) {
			for (Ladrillo ladrillo : piso) {
				if (colision(ladrillo)) {
					// rebote para abajo
					if (velocidadY > 0) { // si está cayendo
						y = ladrillo.getY() - 25 - this.alto / 2;
						enelaire = false;
						velocidadY = 0;
						ensuelo = true; // El Trex está en el suelo

					}
				}
			}
		}

		// si  no está en el suelo, cae
		if (!ensuelo) {
			velocidadY += gravedad;
			y += velocidadY;
			enelaire = true;
			
			
			
		}

		// cuando llega a los bordes cambia de direccion
		if (this.x + this.ancho / 2 >= e.ancho()) {
			this.direccion = -1;
		} else if (this.x - this.ancho / 2 <= 0) {
			this.direccion = 1;
		}
		if (this.direccion == 1) {
			moverderecha();
		} else {
			moverizquierda();
		}
	}

	public void moverderecha() {
		this.x += 0.9;
		this.direccion=1;
	}

	public void moverizquierda() {
		this.x -= 0.9;
		this.direccion=-1;
	}

	public Hueso dispararHueso() {
		return new Hueso(this.x, this.y,this.direccion);
	    

	}
	
	private boolean colision(Ladrillo ladrillo) {
		return this.x + this.ancho / 2 > ladrillo.getX() - ladrillo.getAncho() / 2
				&& this.x - this.ancho / 2 < ladrillo.getX() + ladrillo.getAncho() / 2
				&& this.y + this.alto / 2 > ladrillo.getY() - ladrillo.getAlto() / 2
				&& this.y - this.alto / 2 < ladrillo.getY() + ladrillo.getAlto() / 2;
	}

}
