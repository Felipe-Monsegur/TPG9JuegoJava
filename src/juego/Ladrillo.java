package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Ladrillo {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Color color;
	private Image imag;
	private double angulo;
	private boolean destructible;
	private Image imagM;

	public Ladrillo(double x, double y, double ancho, double alto, Entorno entorno, double random) {
		this.x = x;
		this.y = y;
		this.ancho = 50;
		this.alto = 50;
		this.color = Color.ORANGE;
		this.imag = Herramientas.cargarImagen("ladrillo.png");
		this.imagM = Herramientas.cargarImagen("metal.png");
		// metal o ladrillo
		if (random == 3) {
			this.destructible = false;
		} else {
			this.destructible = true;
		}

	}

	public boolean isDestructible() {
		return destructible;
	}

	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}

	// valores para poder romper el ladrillo y que no se dibuje si esta roto
	

	public void dibujar(Entorno e) {
		if (!destructible) {
			e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
			e.dibujarImagen(imagM, x, y, 0, 1);
		} else if (destructible) {
			e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
			e.dibujarImagen(imag, x, y, 0, 1);
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
}