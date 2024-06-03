package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private int direccion;
	private Image imag;

	// variables de instancia para saltar
	private double velocidadY;
	private double gravedad;
	private boolean enelaire;

//	// Agregar lista de ladrillos para colisiones
//    private ArrayList<Ladrillo> piso;

	public Princesa(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 40;
		this.alto = 50;
		this.direccion = 1;
		this.velocidadY = 0;
		this.gravedad = 0.5;
		this.enelaire = false;
		this.imag = Herramientas.cargarImagen("styles/princesa.png");

	}

	public void dibujar(Entorno e) {
//		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		e.dibujarImagen(imag, x, y - 7, 0, 0.13);
//		if (direccion==1) {
//			this.imag = Herramientas.cargarImagen("princesa.png");
//		}else {
//			this.imag = Herramientas.cargarImagen("princesaIzq.png");
//		}
	}

	public void cambiarImagen() {// Cambia la imagen cuadno la golpean
		if (direccion == 1) {
			this.imag = Herramientas.cargarImagen("styles/princesa golpe.png");
		} else {
			this.imag = Herramientas.cargarImagen("styles/princesaIzq golpe.png");
		}
	}

	public void moverDerecha(Entorno e) {
		if (this.x + this.ancho / 2 < e.ancho())
			this.x += 4;
		this.direccion = 1;
		this.imag = Herramientas.cargarImagen("styles/princesa.png");
	}

	public void moverIzquierda() {
		if (this.x - this.ancho / 2 - 3 >= 0) {
			this.x -= 4;
			this.direccion = -1;
			this.imag = Herramientas.cargarImagen("styles/princesaIzq.png");
		}
	}

	public Bala disparar() {
		return new Bala(this.x, this.y, this.direccion);

	}

	public void mover(Ladrillo[][] pisos) {
		boolean ensuelo = false; // verifica si la princesa está en el suelo

		// verifica colisiones con bloques en todos los pisos
		for (int i = 0; i < pisos.length; i++) {
			for (int j = 0; j < pisos[i].length; j++) {
				Ladrillo ladrillo = pisos[i][j];
				if (ladrillo != null && colision(ladrillo)) {
					if (this.y - this.alto / 2 > ladrillo.getY() - ladrillo.getAlto() / 2) {
						y = ladrillo.getY() + ladrillo.getAlto() / 2 + this.alto / 2;
						velocidadY = -velocidadY * 0.1;
						ensuelo = false;
						// Romper el ladrillo de arriba si es destructible
						if (ladrillo.isDestructible()) {
							pisos[i][j] = null; // ladrillo null "lo rompe"
						}
					} else if (velocidadY > 0) {
						y = ladrillo.getY() - 25 - this.alto / 2;
						enelaire = false;
						velocidadY = 0;
						ensuelo = true;

					}
				}
			}
		}

		// si la princesa no está en el suelo, cae
		if (!ensuelo) {
			velocidadY += gravedad;
			y += velocidadY;
			enelaire = true;
		}
	}

	// hace que el personaje salte
	public void saltar() {
		if (!enelaire) {
			enelaire = true;
			velocidadY = -12.5;
		}
	}

	private boolean colision(Ladrillo ladrillo) {
		return this.x + this.ancho / 2 > ladrillo.getX() - ladrillo.getAncho() / 2
				&& this.x - this.ancho / 2 < ladrillo.getX() + ladrillo.getAncho() / 2
				&& this.y + this.alto / 2 > ladrillo.getY() - ladrillo.getAlto() / 2
				&& this.y - this.alto / 2 < ladrillo.getY() + ladrillo.getAlto() / 2;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}

}
