package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Portal {
	private double x;
    private double y;
    private long startTime;
    private static final int APPEAR_TIME = 2000; // 2 segundos
    private Image imagenPortal;

    public Portal(double x, double y) {
        this.x = x;
        this.y = y;
        this.startTime = System.currentTimeMillis();
        this.imagenPortal = Herramientas.cargarImagen("styles/portal.jpeg");
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarImagen(imagenPortal, x, y-14, 0,0.5);
    }

    public boolean haPasadoTiempo() {
        return System.currentTimeMillis() - startTime >= APPEAR_TIME;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

