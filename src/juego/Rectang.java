package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Rectang {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Color color;
    private Image imag;
    private double angulo;
   

    public Rectang(double x, double y, double ancho, double alto, Color color,Entorno e) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = 50;
        this.color = Color.ORANGE;
        this.imag = Herramientas.cargarImagen("ladrillo.jpeg");
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
        e.dibujarImagen(imag, x, y, 0, 0.12);
		for (int i = 0; i<8.5; i++ ) {
            e.dibujarImagen(imag, (x + i * 47) , y, 0, 0.12); // Dibujar la imagen repetida para la derecha
            e.dibujarImagen(imag, (x - i * 47) , y, 0, 0.12); // Dibujar la imagen repetida para la izquierda
        }
		
    }
}