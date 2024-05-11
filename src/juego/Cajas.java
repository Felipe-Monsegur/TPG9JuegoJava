package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Cajas {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Color color;
    private Image imag;
    private double angulo;

    public Cajas(double x, double y, double ancho, double alto,Entorno e) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = 50;
        this.color = Color.LIGHT_GRAY;
        this.imag = Herramientas.cargarImagen("barril.jpeg");
    }
    
    public void dibujar(Entorno e) {
    	e.dibujarRectangulo(x, y, ancho, alto, 0, color);
//        e.dibujarImagen(imag,x, y, 0, 0.17);
        for (int i = 0; i<2; i++ ) {
            e.dibujarImagen(imag, (x + i * 41) , y+1, 0, 0.168); // Dibujar la imagen repetida para la derecha
            e.dibujarImagen(imag, (x - i * 41) , y+1, 0, 0.168); // Dibujar la imagen repetida para la izquierda
        }
//        e.dibujarRectangulo(x, y, ancho, alto, 0, color);
    }
}