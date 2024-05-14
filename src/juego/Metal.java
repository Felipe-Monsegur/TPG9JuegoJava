package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Metal {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Color color;
    private Image imag;
    private double angulo;

    public Metal(double x, double y, double ancho, double alto,Entorno e) {
        this.x = x;
        this.y = y;
        this.ancho = 150;
        this.alto = 50;
        this.color = Color.LIGHT_GRAY;
        this.imag = Herramientas.cargarImagen("metal.png");
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
	
	public void dibujar(Entorno e) {
    	e.dibujarRectangulo(x, y, ancho, alto, 0, color);
//        e.dibujarImagen(imag,x, y, 0, 0.17);
        for (int i = 0; i<2; i++ ) {
            e.dibujarImagen(imag, (x + i * 50) , y+1, 0, 1); // Dibujar la imagen repetida para la derecha
            e.dibujarImagen(imag, (x - i * 50) , y+1, 0, 1); // Dibujar la imagen repetida para la izquierda
        }
//        e.dibujarRectangulo(x, y, ancho, alto, 0, color);
    }
}