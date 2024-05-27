package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Hueso {
	private double x;
	private double y;
	private double diametro;
	private double velocidad;
	private Image imag;
	private int direccion; // 1 para derecha, -1 para izquierda

	public Hueso(double x, double y, int direccion) {
		this.x = x;
		this.y = y;
		this.diametro = 25;
		this.velocidad = 2;
		this.imag = Herramientas.cargarImagen("hueso.png");
		this.direccion = direccion;
	}

	public void dibujar(Entorno e) {
//		e.dibujarCirculo(this.x, this.y, this.diametro, this.color);
		e.dibujarImagen(imag, x, y, x * 0.02, 1.6);
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

	public boolean colisionConBala(Bala bala) {
		return bala != null && this.x + this.diametro / 2 > bala.getX() - bala.getDiametro() / 2
				&& this.x - this.diametro / 2 < bala.getX() + bala.getDiametro() / 2
				&& this.y + this.diametro / 2 > bala.getY() - bala.getDiametro() / 2
				&& this.y - this.diametro / 2 < bala.getY() + bala.getDiametro() / 2;
	}

	public boolean colisionConPrincesa(Princesa princesa) {
		return princesa != null && this.x + this.diametro / 2 > princesa.getX() - princesa.getAncho() / 2
				&& this.x - this.diametro / 2 < princesa.getX() + princesa.getAncho() / 2
				&& this.y + this.diametro / 2 > princesa.getY() - princesa.getAlto() / 2
				&& this.y - this.diametro / 2 < princesa.getY() + princesa.getAlto() / 2;
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

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}
