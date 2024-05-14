package juego;

import java.awt.Color;
import java.awt.Image;

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
    private boolean roto;

    public Ladrillo(double x, double y, double ancho, double alto,Entorno entorno) {
        this.x = x;
        this.y = y;
        this.ancho = 50;
        this.alto = 50;
        this.color = Color.ORANGE;
        this.imag = Herramientas.cargarImagen("ladrillo.png"); 
        this.roto= false;
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

	
	public boolean isRoto() {
        return roto;
    }
    public void setRoto(boolean roto) {
        this.roto = roto;
    }

	public void dibujar(Entorno e) {
		if(!roto) {
			e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
			 e.dibujarImagen(imag, x, y, 0, 1);
		}
	}
}