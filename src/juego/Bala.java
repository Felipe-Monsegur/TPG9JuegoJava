package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Bala {
	private double x;
	private double y;
	private double diametro;
	private double velocidad;
	private Image imag;
	private int direccion; // 1 para derecha, -1 para izquierda

	public Bala(double x, double y, int direccion) {
		this.x = x;
		this.y = y;
		this.diametro = 25;
		this.velocidad = 5;
		this.imag = Herramientas.cargarImagen("styles/bolaFuego.png");
		this.direccion = direccion;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imag, x, y, x * 0.02, 2.5);
	}

	public void mover() {
		this.x += this.direccion * this.velocidad; // Mover en la dirección correspondiente
	}

	public boolean chocaConEntorno(Entorno e) {
		if (this.x - this.diametro / 2 < 0 || this.x + this.diametro / 2 > e.ancho()) {
			return true;
		}
		return false;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDiametro() {
		return diametro;
	}

	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}

}
