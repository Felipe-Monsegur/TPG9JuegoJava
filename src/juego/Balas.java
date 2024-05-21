package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Balas {
	private double x;
	private double y;
	private double diametro;
	private Color color;
	private double angulo;
	private double velocidad;
	private Image imag;

	public Balas(double x, double y) {
		this.x = x;
		this.y = y;
		this.color = Color.RED;
		this.diametro = 30;
		this.angulo = 0;
		this.velocidad = 5;
		this.imag = Herramientas.cargarImagen("ladrillo.png");
	}
	public void dibujar(Entorno e) {
		e.dibujarCirculo(this.x, this.y, this.diametro, this.color);

		e.dibujarImagen(imag, x, y, x * 0.1, 0.60);
	}
	public void mover() {
		this.x += 5;
//		this.y += angulo * velocidad;
	}
	public boolean chocaConEntorno(Entorno e) {
		if (this.x - this.diametro / 2 < 0 || this.x + this.diametro / 2 > e.ancho()) {
			return true;
		}
		if (this.y - this.diametro / 2 < 0 || this.y + this.diametro / 2 > e.alto()) {
			return true;
		}
		return false;
	}
	public static void agregarBalas(Balas p, Balas[] balas) {
		for (int i = 0; i < balas.length; i++) {
			if (balas[i] == null) {
				balas[i] = p;
				return;
			}
		}
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}

