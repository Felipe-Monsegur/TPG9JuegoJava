package juego;

import java.awt.Color;
import java.util.Random;
import entorno.Entorno;

public class Piso {
    private Rectang rectangIzquierdo;
    private Cajas cajas;
    private Rectang rectangDerecho;

    public Piso(double x, double y, double ancho, double alto, Entorno e) {
        double anchoCajas = ancho / 6; // Ancho de la caja marrón, 1/6 del ancho total
        Random rand = new Random();
        int randomX = rand.nextInt(700-(int)(anchoCajas/2)) + (int)(anchoCajas/2); // Posición x aleatoria dentro del ancho del piso
        int r = randomX;
        this.cajas = new Cajas(r, y, anchoCajas, alto, e);
        this.rectangDerecho = new Rectang(x + (r+(anchoCajas/2)), y, ancho, alto, Color.ORANGE, e);
        this.rectangIzquierdo = new Rectang((r-(anchoCajas/2)) - x, y, ancho, alto, Color.ORANGE, e);
    }

    public void dibujar(Entorno entorno) {
        rectangDerecho.dibujar(entorno);
        cajas.dibujar(entorno);
        rectangIzquierdo.dibujar(entorno);

    }
}