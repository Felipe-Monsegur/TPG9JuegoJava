package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Lava {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double velocidad;
	private Image imag;

	public Lava(double x, double y,Entorno e) {
	        this.x = x;
	        this.y = y;
	        this.ancho = e.ancho();
	        this.alto = e.alto();
	        this.velocidad = 0.2; // Velocidad de ascenso del magma
	        this.imag = Herramientas.cargarImagen("styles/lava.png");
	    }

	public void subirLava() {
        this.y -= this.velocidad; // la lava sube poco a poco
    }  

	public void dibujar(Entorno e) {
		e.dibujarImagen(imag, x, y, 0, 1);

	}
	
	
	 public boolean colisionConPrincesa(Princesa princesa) {
	        return this.x + this.ancho / 2 > princesa.getX() - princesa.getAncho() / 2 &&
	               this.x - this.ancho / 2 < princesa.getX() + princesa.getAncho() / 2 &&
	               this.y + this.alto / 2 > princesa.getY() - princesa.getAlto() / 2 &&
	               this.y - this.alto / 2 < princesa.getY() + princesa.getAlto() / 2;
	    }
	
}
